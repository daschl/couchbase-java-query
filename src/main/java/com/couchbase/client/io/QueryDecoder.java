package com.couchbase.client.io;

import com.couchbase.client.mapping.QueryResult;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.util.CharsetUtil;
import net.spy.memcached.ops.OperationStatus;

import java.util.Queue;


public class QueryDecoder extends SimpleChannelInboundHandler<HttpObject> {

  private final Queue<QueryEvent> queue;
  private QueryEvent currentEvent;

  public QueryDecoder(Queue<QueryEvent> queue) {
    this.queue = queue;
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
    if (msg instanceof HttpResponse) {
      currentEvent = queue.poll();
      HttpResponse response = (HttpResponse) msg;

    }
    if (msg instanceof HttpContent) {
      HttpContent content = (HttpContent) msg;

      QueryResult queryResult = new QueryResult();
      queryResult.resultSet = content.content().toString(CharsetUtil.UTF_8);
      currentEvent.getFuture().set(queryResult, new OperationStatus(true, "200!"));
      currentEvent.getLatch().countDown();
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    System.out.println(cause.getStackTrace());
  }

}
