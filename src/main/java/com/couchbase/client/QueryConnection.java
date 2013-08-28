package com.couchbase.client;

import com.couchbase.client.internal.HttpFuture;
import com.couchbase.client.query.QueryInitializer;
import com.couchbase.client.query.QueryEvent;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import net.spy.memcached.compat.SpyObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class QueryConnection extends SpyObject {

  /**
   * Current default query port of the engine.
   */
  public static final int QUERY_PORT = 8093;

  /**
   * Wait N milliseconds for all connect futures to complete.
   */
  public static final int CONNECT_TIMEOUT = 5000;

  /**
   * Contains all currently connected and alive channels.
   */
  List<Channel> connectedChannels = new ArrayList<Channel>();

  EventLoopGroup group = new NioEventLoopGroup();

  public QueryConnection(List<String> nodes) {

    Class<? extends SocketChannel> channel = NioSocketChannel.class;
    Bootstrap bootstrap = new Bootstrap()
      .group(group)
      .channel(channel)
      .handler(new QueryInitializer());

    List<ChannelFuture> connectFutures = new ArrayList<ChannelFuture>();
    for (String node : nodes) {
      connectFutures.add(bootstrap.connect(node, QUERY_PORT));
    }

    long startTime = System.nanoTime();
    long connectTimeout = TimeUnit.MILLISECONDS.toNanos(CONNECT_TIMEOUT);
    while (connectFutures.size() > 0 && System.nanoTime() - startTime <= connectTimeout) {
      Iterator<ChannelFuture> iterator = connectFutures.iterator();
      while (iterator.hasNext()) {
        ChannelFuture connectFuture = iterator.next();
        if (!connectFuture.isDone()) {
          continue;
        }

        if (connectFuture.isSuccess()) {
          connectedChannels.add(connectFuture.channel());
        } else {
          getLogger().error("Could not connect to Query node, skipping: "
            + connectFuture.cause());
        }

        iterator.remove();
      }
    }

    if (connectedChannels.size() == 0) {
      throw new BootstrapException("Could not connect to at least one query node, stopping bootstrap.");
    }
  }

  public HttpFuture<Object> execute(String query) {
    Channel chan = connectedChannels.get(0); // always use first channel for now


    CountDownLatch futureLatch = new CountDownLatch(1);
    HttpFuture<Object> future = new HttpFuture<Object>(futureLatch, 10000);

    ChannelFuture channelFuture = chan.write(new QueryEvent(query, future, futureLatch));

    final CountDownLatch latch = new CountDownLatch(1);
    channelFuture.addListener(new ChannelFutureListener() {
      @Override
      public void operationComplete(ChannelFuture channelFuture) throws Exception {
        latch.countDown();
      }
    });
    try {
      latch.await();
    } catch (InterruptedException e) {
      getLogger().warn("Got interrupted while writing Query, cancelling operation.");
      future.cancel(true);
    }

    return future;
  }

  public void shutdown() {
    try {
      group.shutdownGracefully().sync();
    } catch (InterruptedException e) {
      throw new IllegalStateException("Interrupted while shutting down.");
    }
  }

}
