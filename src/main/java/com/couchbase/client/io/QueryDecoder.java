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

import com.couchbase.client.mapping.QueryResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.util.CharsetUtil;
import net.spy.memcached.ops.OperationStatus;

import java.util.Queue;

/**
 * Decode incoming {@link HttpObject}s and complete
 * {@link com.couchbase.client.internal.HttpFuture}s.
 *
 * Every response that arrives from the server, comes into this decoder.
 * Once the HTTP header is received, a new operation is picked from the queue and the
 * JSON response is parsed. Finally, the future is completed.
 */
public class QueryDecoder extends SimpleChannelInboundHandler<HttpObject> {

  /**
   * Contains the queue of current events to decode.
   */
  private final Queue<QueryEvent> queue;

  /**
   * Contains the current event.
   */
  private QueryEvent currentEvent;

  /**
   * Contains the current operation status for the event.
   */
  private OperationStatus currentStatus;

  /**
   * Contains the current chunks to parse.
   */
  private StringBuilder contentBuffer;

  /**
   * Cache the Jackson object mapper.
   */
  ObjectMapper mapper;

  /**
   * Create a new {@link QueryDecoder}.
   *
   * @param queue
   */
  public QueryDecoder(Queue<QueryEvent> queue) {
    this.queue = queue;
    contentBuffer = new StringBuilder();
    mapper = new ObjectMapper();

    clearForNextEvent();
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
    if (msg instanceof HttpResponse) {
      currentEvent = queue.poll();
      HttpResponse response = (HttpResponse) msg;
      if (response.getStatus().code() != 200) {
        setCurrentEventStatus(false, response.getStatus().toString());
      }
    }

    if (msg instanceof HttpContent) {
      HttpContent content = (HttpContent) msg;
      contentBuffer.append(content.content().toString(CharsetUtil.UTF_8));

      if (msg instanceof LastHttpContent) {
        QueryResult result = decodeContentBuffer();
        setCurrentEventStatus(true, "Success");
        currentEvent.getFuture().set(result, currentStatus);
        currentEvent.getLatch().countDown();
        clearForNextEvent();
      }
    }
  }

  /**
   * Wrapper method to set the status only if it wasn't set before.
   *
   * @param success success or not.
   * @param msg the message to add.
   */
  private void setCurrentEventStatus(boolean success, String msg) {
    if (currentStatus == null) {
      currentStatus = new OperationStatus(success, msg);
    }
  }

  /**
   * Reset certain variables to their "fresh" state.
   */
  private void clearForNextEvent() {
    currentStatus = null;
    contentBuffer.setLength(0);
  }

  /**
   * Decode the raw JSON string into a {@link QueryResult}.
   *
   * @return the parsed result.
   */
  private QueryResult decodeContentBuffer() {
    QueryResult result = null;
    try {
       result = mapper.readValue(contentBuffer.toString(), QueryResult.class);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return result;
  }

  /**
   * Handle exceptions.
   *
   * @param ctx handler context.
   * @param cause cause of exception.
   */
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
  }

}
