package com.couchbase.client;

import com.couchbase.client.internal.HttpFuture;

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

  //@Test
  public void foo() throws Exception {
    QueryConnection connection = new QueryConnection(nodes);
    for (int i = 1; i < 100; i++) {
      System.out.println("Iteration " + i);
      HttpFuture<Object> future = connection.execute("SELECT * FROM default LIMIT " + i);
      System.out.println(future.get());
    }
    connection.shutdown();
  }

  @Test
  public void benchmarkOneThreadSyncPerformance() throws Exception {
    QueryConnection connection = new QueryConnection(nodes);
    final int iterations = 1000;
    final long start = System.nanoTime();
    for (int i = 0; i < iterations; i++) {
      HttpFuture<Object> future = connection.execute("SELECT * FROM default LIMIT " + i);
      future.get();
    }
    final long end = System.nanoTime();

    System.out.println(iterations + " iterations took: " + (end - start) / 1000000 + " milliseconds");
    System.out.println("That is ")
    connection.shutdown();
  }

}
