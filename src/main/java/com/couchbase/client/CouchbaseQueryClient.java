package com.couchbase.client;

import com.couchbase.client.internal.HttpFuture;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    }
    return parsed;
  }

  public HttpFuture<Object> asyncQuery(String query) {
    return queryConnection.execute(query);
  }

  public Object query(String query) {
    //return asyncQuery(query).get();
    return null;
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
