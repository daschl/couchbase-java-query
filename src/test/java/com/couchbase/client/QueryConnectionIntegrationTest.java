package com.couchbase.client;

import com.couchbase.client.internal.HttpFuture;

import com.couchbase.client.mapping.QueryResult;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class QueryConnectionIntegrationTest {

  List<String> nodes = new ArrayList<String>();

  @Before
  public void setup() {
    nodes.add("127.0.0.1");
  }

  @Test
  public void benchmarkOneThreadSyncPerformance() throws Exception {
    QueryConnection connection = new QueryConnection(nodes);
    final int iterations = 1000;
    final long start = System.nanoTime();
    for (int i = 0; i < iterations; i++) {
      HttpFuture<QueryResult> future = connection.execute("SELECT * FROM default LIMIT " + i);
      System.out.println(future.get().resultSet);
    }
    final long end = System.nanoTime();

    final long difference = (end - start) / 1000000;
    System.out.println("One Thread Sync: " + iterations + " iterations took: " + difference + " milliseconds");
    System.out.println("That is " + (iterations / difference) * 1000 + "calls per second");
    connection.shutdown();
  }

}
