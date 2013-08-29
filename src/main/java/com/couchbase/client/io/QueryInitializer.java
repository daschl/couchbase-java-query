package com.couchbase.client.io;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;


public class QueryInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ch.pipeline()
      .addLast(new HttpClientCodec())
      .addLast(new QueryCodec());
  }
}
