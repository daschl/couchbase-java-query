package com.couchbase.client.query;

import com.couchbase.client.internal.HttpFuture;

import java.util.concurrent.CountDownLatch;

public final class QueryEvent {

  private final String query;
  private final HttpFuture<Object> future;
  private final CountDownLatch latch;

  public QueryEvent(String query, HttpFuture<Object> future, CountDownLatch latch) {
    this.query = query;
    this.future = future;
    this.latch = latch;
  }

  public String getQuery() {
    return query;
  }

  public HttpFuture<Object> getFuture() {
    return future;
  }

  public CountDownLatch getLatch() {
    return latch;
  }

}
