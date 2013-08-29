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
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class QueryConnectionIntegrationTest {

  List<String> nodes = new ArrayList<String>();

  @Before
  public void setup() {
    nodes.add("127.0.0.1");
  }

  @Test
  public void benchmarkOneThreadSyncPerformance() throws Exception {
    QueryConnection connection = new QueryConnection(nodes);
    final int iterations = 100;
    final long start = System.nanoTime();
    for (int i = 0; i < iterations; i++) {
      HttpFuture<QueryResult> future = connection.execute("SELECT * FROM beer-sample LIMIT 3");
      assertTrue(future.get().isSuccess());
    }
    final long end = System.nanoTime();

    final long difference = (end - start) / 1000000;
    System.out.println("One Thread Sync: " + iterations + " iterations took: " + difference + " milliseconds");
    connection.shutdown();
  }

}
