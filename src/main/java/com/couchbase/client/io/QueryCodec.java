package com.couchbase.client.io;

import io.netty.channel.CombinedChannelDuplexHandler;

import java.util.ArrayDeque;
import java.util.Queue;


public final class QueryCodec extends CombinedChannelDuplexHandler<QueryDecoder, QueryEncoder> {

  private Queue<QueryEvent> queue = new ArrayDeque<QueryEvent>();

  public QueryCodec() {
    init(new QueryDecoder(queue), new QueryEncoder(queue));
  }

}
