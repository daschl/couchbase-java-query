/**
 * Copyright (C) 2013 Couchbase, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALING
 * IN THE SOFTWARE.
 */

package com.couchbase.client;

import com.couchbase.client.internal.HttpFuture;
import com.couchbase.client.mapping.QueryResult;
import net.spy.memcached.CASResponse;
import net.spy.memcached.PersistTo;
import net.spy.memcached.ReplicateTo;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CouchbaseQueryClient extends CouchbaseClientProxy {

  private final CouchbaseClient couchbaseClient;
  private final QueryConnection queryConnection;
  private final String bucketName;

  /**
   * Create a new {@link CouchbaseQueryClient} with default settings.
   *
   * Note that by default, the code assumes that both the Query Engine and the Couchbase Server
   * run on localhost (127.0.0.1) and the default bucket is used. This is suitable for development,
   * but for production use cases other constructors should be used.
   */
  public CouchbaseQueryClient() {
    this(Arrays.asList("127.0.0.1"), "default", "");
  }

  /**
   * Create a new {@link CouchbaseQueryClient}.
   *
   * The node list used here will be used for both the Query Engine and Couchbase Server.
   *
   * @param nodes list of nodes to bootstrap.
   * @param bucket the bucket name.
   * @param password the bucket password.
   */
  public CouchbaseQueryClient(List<String> nodes, String bucket, String password) {
    this(nodes, nodes, bucket, password);
  }

  /**
   * Create a new {@link CouchbaseQueryClient}.
   *
   * @param couchbaseNodes nodes of the couchbase cluster.
   * @param queryNodes nodes of the query engine.
   * @param bucket bucket name.
   * @param password bucket password.
   */
  public CouchbaseQueryClient(List<String> couchbaseNodes, List<String> queryNodes, String bucket, String password) {
    List<URI> parsedNodes = parseCouchbaseNodes(couchbaseNodes);

    CouchbaseConnectionFactory factory = null;
    try {
      CouchbaseConnectionFactoryBuilder builder = new CouchbaseConnectionFactoryBuilder();
      factory = builder.buildCouchbaseConnection(parsedNodes, bucket, password);
      couchbaseClient = new CouchbaseClient(factory);
    } catch (Exception ex) {
      throw new BootstrapException("Could not bootstrap the CouchbaseClient: " + ex);
    }

    try {
    queryConnection = new QueryConnection(queryNodes, factory);
    } catch (Exception ex) {
      throw new BootstrapException("Could not bootstrap the QueryClient: " + ex);
    }

    bucketName = bucket;
  }

  private List<URI> parseCouchbaseNodes(List<String> nodes) {
    List<URI> parsed = new ArrayList<URI>();
    for (String node : nodes) {
      try {
        parsed.add(new URI("http://" + node + ":8091/pools"));
      } catch (Exception ex) {
        throw new IllegalArgumentException("Illegal node string found.", ex);
      }
    }
    return parsed;
  }

  public HttpFuture<QueryResult> asyncQuery(String query) {
    return queryConnection.execute(preprocessQuery(query));
  }

  public QueryResult query(String query) {
    HttpFuture<QueryResult> future = asyncQuery(query);

    try {
      return asyncQuery(query).get();
    } catch (ExecutionException ex) {
      throw new RuntimeException("Got exception while waiting on io", ex);
    } catch (InterruptedException ex) {
      throw new RuntimeException("Got exception while waiting on io", ex);
    }
  }

  /**
   * Provides the underlying {@link CouchbaseClient} for proxying.
   *
   * @return the underlying {@link CouchbaseClient}.
   */
  @Override
  protected CouchbaseClient getCouchbaseClient() {
    return couchbaseClient;
  }

  @Override
  public void shutdown() {
    shutdown(-1, null);
  }

  @Override
  public boolean shutdown(long timeout, TimeUnit unit) {
    if (timeout == -1) {
      couchbaseClient.shutdown();
    } else {
      couchbaseClient.shutdown(timeout, unit);
    }

    queryConnection.shutdown();
    return true;
  }

  /**
   * Preprocess the Query and replace supported tokens.
   *
   * @param original the original query string.
   * @return the processed query string.
   */
  private String preprocessQuery(String original) {
    return original.replaceAll("\\{bucket\\}", bucketName);
  }

}
