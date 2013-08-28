package com.couchbase.client.query;

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
  private QueryEvent nextEvent;

  public QueryDecoder(Queue<QueryEvent> queue) {
    this.queue = queue;
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
    if (msg instanceof HttpResponse) {
      HttpResponse response = (HttpResponse) msg;
      nextEvent = queue.poll();
    }
    if (msg instanceof HttpContent) {
      HttpContent content = (HttpContent) msg;
      nextEvent.getFuture().set(content.content().toString(CharsetUtil.UTF_8), new OperationStatus(true, "200!"));
      nextEvent.getLatch().countDown();
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    System.out.println(cause.getStackTrace());
  }

}
