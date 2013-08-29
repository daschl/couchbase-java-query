package com.couchbase.client;

import com.couchbase.client.internal.HttpFuture;
import com.couchbase.client.mapping.QueryResult;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CouchbaseQueryClient extends CouchbaseClientProxy {

  private final CouchbaseClient couchbaseClient;
  private final QueryConnection queryConnection;

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

  public CouchbaseQueryClient(List<String> nodes, String bucket, String password) {
    this(nodes, nodes, bucket, password);
  }

  public CouchbaseQueryClient(List<String> couchbaseNodes, List<String> queryNodes, String bucket, String password) {
    List<URI> parsedNodes = parseCouchbaseNodes(couchbaseNodes);

    try {
      couchbaseClient = new CouchbaseClient(parsedNodes, bucket, password);
    } catch (Exception ex) {
      throw new BootstrapException("Could not bootstrap the CouchbaseClient: " + ex);
    }

    try {
    queryConnection = new QueryConnection(queryNodes);
    } catch (Exception ex) {
      throw new BootstrapException("Could not bootstrap the QueryClient: " + ex);
    }
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
    return queryConnection.execute(query);
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

}
