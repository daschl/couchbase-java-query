package com.couchbase.client.io;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

import java.util.List;
import java.util.Queue;


public class QueryEncoder extends MessageToMessageEncoder<QueryEvent> {

  private final Queue<QueryEvent> queue;

  public QueryEncoder(Queue<QueryEvent> queue) {
    this.queue = queue;
  }

  @Override
  protected void encode(ChannelHandlerContext ctx, QueryEvent ev, List<Object> out) throws Exception {
    ByteBuf queryBuf = Unpooled.copiedBuffer(ev.getQuery(), CharsetUtil.UTF_8);
    HttpRequest request = new DefaultFullHttpRequest(
      HttpVersion.HTTP_1_1,
      HttpMethod.POST,
      "/query",
      queryBuf
    );

    request.headers().set("Content-Length", queryBuf.readableBytes());
    request.headers().set("Content-Type", "text/plain");

    out.add(request);
    queue.add(ev);
  }

}
