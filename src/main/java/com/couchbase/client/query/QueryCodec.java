package com.couchbase.client.query;

import io.netty.channel.CombinedChannelDuplexHandler;

import java.util.ArrayDeque;
import java.util.Queue;


public class QueryCodec extends CombinedChannelDuplexHandler<QueryDecoder, QueryEncoder> {

  private Queue<QueryEvent> queue = new ArrayDeque<QueryEvent>();

  public QueryCodec() {
    init(new QueryDecoder(queue), new QueryEncoder(queue));
  }

}
