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

import java.util.*;


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

  private void setCurrentEventStatus(boolean success, String msg) {
    if (currentStatus == null) {
      currentStatus = new OperationStatus(success, msg);
    }
  }

  private void clearForNextEvent() {
    currentStatus = null;
    contentBuffer.setLength(0);
  }

  private QueryResult decodeContentBuffer() {
    QueryResult result = null;
    try {
       result = mapper.readValue(contentBuffer.toString(), QueryResult.class);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return result;
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
  }

}
