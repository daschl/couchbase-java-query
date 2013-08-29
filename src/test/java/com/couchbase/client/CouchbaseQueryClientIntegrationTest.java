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

import com.couchbase.client.mapping.QueryResult;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CouchbaseQueryClientIntegrationTest {

  List<String> nodes = new ArrayList<String>();

  @Before
  public void setup() {
    nodes.add("127.0.0.1");
  }

  @Test
  public void shouldBootstrap() {
    CouchbaseQueryClient client = new CouchbaseQueryClient(nodes, nodes, "beer-sample", "");
    QueryResult query = client.query("SELECT * FROM beer-sample LIMIT 1");

    assertTrue(query.isSuccess());

    client.shutdown();
  }

  @Test(expected = BootstrapException.class)
  public void shouldThrowOnUnknownHost() {
    new CouchbaseQueryClient(Arrays.asList("unknownHostname"), "default", "");
  }

  @Test(expected = BootstrapException.class)
  public void shouldThrowOnEmptyHost() {
    new CouchbaseQueryClient(Arrays.asList(""), "default", "");
  }

  @Test
  public void shouldWorkWithInvalidQuery() {
    CouchbaseQueryClient client = new CouchbaseQueryClient(nodes, nodes, "beer-sample", "");
    QueryResult query = client.query("SELECT * FROM beer-sample LIMI 1");

    assertFalse(query.isSuccess());
    assertEquals(4100, query.getError().getCode());
    assertEquals("Parse Error", query.getError().getMessage());

    client.shutdown();
  }

}
