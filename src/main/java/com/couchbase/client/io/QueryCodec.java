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

import io.netty.channel.CombinedChannelDuplexHandler;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * The {@link QueryCodec} provides encoding and decoding of Queries.
 *
 * The purpose of the codec is to enable both the {@link QueryEncoder} and the
 * {@link QueryDecoder} at the same time and providing a Queue through which both
 * share request/response events.
 */
public final class QueryCodec extends CombinedChannelDuplexHandler<QueryDecoder, QueryEncoder> {

  /**
   * Contains a queue of {@link QueryEvent}s to be processed.
   */
  private Queue<QueryEvent> queue = new ArrayDeque<QueryEvent>();

  /**
   * Create a new {@link QueryCodec}.
   */
  public QueryCodec() {
    init(new QueryDecoder(queue), new QueryEncoder(queue));
  }

}
