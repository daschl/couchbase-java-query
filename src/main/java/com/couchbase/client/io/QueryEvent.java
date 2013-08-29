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

package com.couchbase.client.io;

import com.couchbase.client.internal.HttpFuture;

import java.util.concurrent.CountDownLatch;

/**
 * Contains every information that belongs to a certain event.
 *
 * @param <T> type of the future containment.
 */
public final class QueryEvent<T> {

  /**
   * Contains the original query string.
   */
  private final String query;

  /**
   * The future to complete.
   */
  private final HttpFuture<T> future;

  /**
   * The latch to count down when done.
   */
  private final CountDownLatch latch;

  public QueryEvent(String query, HttpFuture<T> future, CountDownLatch latch) {
    this.query = query;
    this.future = future;
    this.latch = latch;
  }

  public String getQuery() {
    return query;
  }

  public HttpFuture<T> getFuture() {
    return future;
  }

  public CountDownLatch getLatch() {
    return latch;
  }

}
