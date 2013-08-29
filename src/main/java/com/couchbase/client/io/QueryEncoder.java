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
