package com.couchbase.client.io;

import com.couchbase.client.internal.HttpFuture;

import java.util.concurrent.CountDownLatch;

public final class QueryEvent<T> {

  private final String query;
  private final HttpFuture<T> future;
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
