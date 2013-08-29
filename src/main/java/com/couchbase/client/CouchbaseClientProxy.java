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

package com.couchbase.client;

import com.couchbase.client.internal.HttpFuture;
import com.couchbase.client.internal.ReplicaGetFuture;
import com.couchbase.client.protocol.views.*;
import net.spy.memcached.*;
import net.spy.memcached.compat.SpyObject;
import net.spy.memcached.internal.BulkFuture;
import net.spy.memcached.internal.OperationFuture;
import net.spy.memcached.transcoders.Transcoder;

import java.io.UnsupportedEncodingException;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

abstract class CouchbaseClientProxy extends SpyObject implements CouchbaseClientIF {

  abstract CouchbaseClient getCouchbaseClient();
  public abstract void shutdown();
  public abstract boolean shutdown(long timeout, TimeUnit unit);

  @Override
  public Future<CASValue<Object>> asyncGetAndLock(String key, int exp) {
    return getCouchbaseClient().asyncGetAndLock(key, exp);
  }

  @Override
  public <T> Future<CASValue<T>> asyncGetAndLock(String key, int exp, Transcoder<T> tc) {
    return getCouchbaseClient().asyncGetAndLock(key, exp, tc);
  }

  @Override
  public <T> CASValue<T> getAndLock(String key, int exp, Transcoder<T> tc) {
    return getCouchbaseClient().getAndLock(key, exp, tc);
  }

  @Override
  public CASValue<Object> getAndLock(String key, int exp) {
    return getCouchbaseClient().getAndLock(key, exp);
  }

  @Override
  public <T> OperationFuture<Boolean> asyncUnlock(String key, long casId, Transcoder<T> tc) {
    return getCouchbaseClient().asyncUnlock(key, casId, tc);
  }

  @Override
  public OperationFuture<Boolean> asyncUnlock(String key, long casId) {
    return getCouchbaseClient().asyncUnlock(key,casId);
  }

  @Override
  public <T> Boolean unlock(String key, long casId, Transcoder<T> tc) {
    return getCouchbaseClient().unlock(key, casId, tc);
  }

  @Override
  public Boolean unlock(String key, long casId) {
    return getCouchbaseClient().unlock(key, casId);
  }

  @Override
  public Map<MemcachedNode, ObserveResponse> observe(String key, long cas) {
    return getCouchbaseClient().observe(key, cas);
  }

  @Override
  public void observePoll(String key, long cas, PersistTo persist, ReplicateTo replicate, boolean isDelete) {
    getCouchbaseClient().observePoll(key, cas, persist, replicate, isDelete);
  }

  @Override
  public OperationFuture<Boolean> set(String key, Object value) {
    return getCouchbaseClient().set(key, value);
  }

  @Override
  public OperationFuture<Boolean> set(String key, int exp, Object value, PersistTo persist) {
    return getCouchbaseClient().set(key, exp, value, persist);
  }

  @Override
  public OperationFuture<Boolean> set(String key, Object value, PersistTo persist) {
    return getCouchbaseClient().set(key, value, persist);
  }

  @Override
  public OperationFuture<Boolean> set(String key, int exp, Object value, ReplicateTo replicate) {
    return getCouchbaseClient().set(key, exp, value, replicate);
  }

  @Override
  public OperationFuture<Boolean> set(String key, Object value, ReplicateTo replicate) {
    return getCouchbaseClient().set(key, value, replicate);
  }

  @Override
  public OperationFuture<Boolean> set(String key, int exp, Object value, PersistTo persist, ReplicateTo replicate) {
    return getCouchbaseClient().set(key, exp, value, persist, replicate);
  }

  @Override
  public OperationFuture<Boolean> set(String key, Object value, PersistTo persist, ReplicateTo replicate) {
    return getCouchbaseClient().set(key, value, persist, replicate);
  }

  @Override
  public OperationFuture<Boolean> add(String key, Object value) {
    return getCouchbaseClient().add(key, value);
  }

  @Override
  public OperationFuture<Boolean> add(String key, int exp, Object value, PersistTo persist) {
    return getCouchbaseClient().add(key, exp, value, persist);
  }

  @Override
  public OperationFuture<Boolean> add(String key, Object value, PersistTo persist) {
    return getCouchbaseClient().add(key, value, persist);
  }

  @Override
  public OperationFuture<Boolean> add(String key, int exp, Object value, ReplicateTo replicate) {
    return getCouchbaseClient().add(key, exp, value, replicate);
  }

  @Override
  public OperationFuture<Boolean> add(String key, Object value, ReplicateTo replicate) {
    return getCouchbaseClient().add(key, value, replicate);
  }

  @Override
  public OperationFuture<Boolean> add(String key, int exp, Object value, PersistTo persist, ReplicateTo replicate) {
    return getCouchbaseClient().add(key, exp, value, persist, replicate);
  }

  @Override
  public OperationFuture<Boolean> add(String key, Object value, PersistTo persist, ReplicateTo replicate) {
    return getCouchbaseClient().add(key, value, persist, replicate);
  }

  @Override
  public OperationFuture<Boolean> replace(String key, Object value) {
    return getCouchbaseClient().replace(key, value);
  }

  @Override
  public OperationFuture<Boolean> replace(String key, int exp, Object value, PersistTo persist) {
    return getCouchbaseClient().replace(key, exp, value, persist);
  }

  @Override
  public OperationFuture<Boolean> replace(String key, Object value, PersistTo persist) {
    return getCouchbaseClient().replace(key, value, persist);
  }

  @Override
  public OperationFuture<Boolean> replace(String key, int exp, Object value, ReplicateTo replicate) {
    return getCouchbaseClient().replace(key, exp, value, replicate);
  }

  @Override
  public OperationFuture<Boolean> replace(String key, Object value, ReplicateTo replicate) {
    return getCouchbaseClient().replace(key, value, replicate);
  }

  @Override
  public OperationFuture<Boolean> replace(String key, int exp, Object value, PersistTo persist, ReplicateTo replicate) {
    return getCouchbaseClient().replace(key, exp, value, persist, replicate);
  }

  @Override
  public OperationFuture<Boolean> replace(String key, Object value, PersistTo persist, ReplicateTo replicate) {
    return getCouchbaseClient().replace(key, value, persist, replicate);
  }

  @Override
  public CASResponse cas(String key, long cas, Object value, PersistTo req, ReplicateTo rep) {
    return getCouchbaseClient().cas(key, cas, value, req, rep);
  }

  @Override
  public CASResponse cas(String key, long cas, Object value, PersistTo req) {
    return getCouchbaseClient().cas(key, cas, value, req);
  }

  @Override
  public CASResponse cas(String key, long cas, Object value, ReplicateTo rep) {
    return getCouchbaseClient().cas(key, cas, value, rep);
  }

  @Override
  public OperationFuture<Boolean> delete(String key, PersistTo persist) {
    return getCouchbaseClient().delete(key, persist);
  }

  @Override
  public OperationFuture<Boolean> delete(String key, PersistTo persist, ReplicateTo replicate) {
    return getCouchbaseClient().delete(key, persist, replicate);
  }

  @Override
  public OperationFuture<Boolean> delete(String key, ReplicateTo replicate) {
    return getCouchbaseClient().delete(key, replicate);
  }

  @Override
  public int getNumVBuckets() {
    return getCouchbaseClient().getNumVBuckets();
  }

  @Override
  public HttpFuture<Boolean> asyncCreateDesignDoc(DesignDocument doc) throws UnsupportedEncodingException {
    return getCouchbaseClient().asyncCreateDesignDoc(doc);
  }

  @Override
  public HttpFuture<Boolean> asyncCreateDesignDoc(String name, String value) throws UnsupportedEncodingException {
    return getCouchbaseClient().asyncCreateDesignDoc(name, value);
  }

  @Override
  public HttpFuture<Boolean> asyncDeleteDesignDoc(String name) throws UnsupportedEncodingException {
    return getCouchbaseClient().asyncDeleteDesignDoc(name);
  }

  @Override
  public HttpFuture<DesignDocument> asyncGetDesignDocument(String designDocumentName) {
    return getCouchbaseClient().asyncGetDesignDocument(designDocumentName);
  }

  @Override
  public Boolean createDesignDoc(DesignDocument doc) {
    return getCouchbaseClient().createDesignDoc(doc);
  }

  @Override
  public Boolean deleteDesignDoc(String name) {
    return getCouchbaseClient().deleteDesignDoc(name);
  }

  @Override
  public DesignDocument getDesignDocument(String designDocumentName) {
    return getCouchbaseClient().getDesignDocument(designDocumentName);
  }

  @Override
  public Object getFromReplica(String key) {
    return getCouchbaseClient().getFromReplica(key);
  }

  @Override
  public <T> T getFromReplica(String key, Transcoder<T> tc) {
    return getCouchbaseClient().getFromReplica(key, tc);
  }

  @Override
  public ReplicaGetFuture<Object> asyncGetFromReplica(String key) {
    return getCouchbaseClient().asyncGetFromReplica(key);
  }

  @Override
  public <T> ReplicaGetFuture<T> asyncGetFromReplica(String key, Transcoder<T> tc) {
    return getCouchbaseClient().asyncGetFromReplica(key, tc);
  }

  @Override
  public HttpFuture<View> asyncGetView(String designDocumentName, String viewName) {
    return getCouchbaseClient().asyncGetView(designDocumentName, viewName);
  }

  @Override
  public HttpFuture<SpatialView> asyncGetSpatialView(String designDocumentName, String viewName) {
    return getCouchbaseClient().asyncGetSpatialView(designDocumentName, viewName);
  }

  @Override
  public HttpFuture<ViewResponse> asyncQuery(AbstractView view, Query query) {
    return getCouchbaseClient().asyncQuery(view, query);
  }

  @Override
  public ViewResponse query(AbstractView view, Query query) {
    return getCouchbaseClient().query(view, query);
  }

  @Override
  public Paginator paginatedQuery(View view, Query query, int docsPerPage) {
    return getCouchbaseClient().paginatedQuery(view, query, docsPerPage);
  }

  @Override
  public View getView(String designDocumentName, String viewName) {
    return getCouchbaseClient().getView(designDocumentName, viewName);
  }

  @Override
  public SpatialView getSpatialView(String designDocumentName, String viewName) {
    return getCouchbaseClient().getSpatialView(designDocumentName, viewName);
  }

  @Override
  public OperationFuture<Map<String, String>> getKeyStats(String key) {
    return getCouchbaseClient().getKeyStats(key);
  }

  @Override
  public Collection<SocketAddress> getAvailableServers() {
    return getCouchbaseClient().getAvailableServers();
  }

  @Override
  public Collection<SocketAddress> getUnavailableServers() {
    return getCouchbaseClient().getUnavailableServers();
  }

  @Override
  public Transcoder<Object> getTranscoder() {
    return getCouchbaseClient().getTranscoder();
  }

  @Override
  public NodeLocator getNodeLocator() {
    return getCouchbaseClient().getNodeLocator();
  }

  @Override
  public Future<Boolean> append(long cas, String key, Object val) {
    return getCouchbaseClient().append(cas, key, val);
  }

  @Override
  public Future<Boolean> append(String key, Object val) {
    return getCouchbaseClient().append(key, val);
  }

  @Override
  public <T> Future<Boolean> append(long cas, String key, T val, Transcoder<T> tc) {
    return getCouchbaseClient().append(cas, key, val, tc);
  }

  @Override
  public <T> Future<Boolean> append(String key, T val, Transcoder<T> tc) {
    return getCouchbaseClient().append(key, val, tc);
  }

  @Override
  public Future<Boolean> prepend(long cas, String key, Object val) {
    return getCouchbaseClient().prepend(cas, key, val);
  }

  @Override
  public Future<Boolean> prepend(String key, Object val) {
    return getCouchbaseClient().prepend(key, val);
  }

  @Override
  public <T> Future<Boolean> prepend(long cas, String key, T val, Transcoder<T> tc) {
    return getCouchbaseClient().prepend(cas, key, val, tc);
  }

  @Override
  public <T> Future<Boolean> prepend(String key, T val, Transcoder<T> tc) {
    return getCouchbaseClient().prepend(key, val, tc);
  }

  @Override
  public <T> Future<CASResponse> asyncCAS(String key, long casId, T value, Transcoder<T> tc) {
    return getCouchbaseClient().asyncCAS(key, casId, value, tc);
  }

  @Override
  public Future<CASResponse> asyncCAS(String key, long casId, Object value) {
    return getCouchbaseClient().asyncCAS(key, casId, value);
  }

  @Override
  public <T> CASResponse cas(String key, long casId, int exp, T value, Transcoder<T> tc) {
    return getCouchbaseClient().cas(key, casId, exp, value, tc);
  }

  @Override
  public CASResponse cas(String key, long casId, Object value) {
    return getCouchbaseClient().cas(key, casId, value);
  }

  @Override
  public <T> Future<Boolean> add(String key, int exp, T o, Transcoder<T> tc) {
    return getCouchbaseClient().add(key, exp, o, tc);
  }

  @Override
  public Future<Boolean> add(String key, int exp, Object o) {
    return getCouchbaseClient().add(key, exp, o);
  }

  @Override
  public <T> Future<Boolean> set(String key, int exp, T o, Transcoder<T> tc) {
    return getCouchbaseClient().set(key, exp, o, tc);
  }

  @Override
  public Future<Boolean> set(String key, int exp, Object o) {
    return getCouchbaseClient().set(key, exp, o);
  }

  @Override
  public <T> Future<Boolean> replace(String key, int exp, T o, Transcoder<T> tc) {
    return getCouchbaseClient().replace(key, exp, o, tc);
  }

  @Override
  public Future<Boolean> replace(String key, int exp, Object o) {
    return getCouchbaseClient().replace(key, exp, o);
  }

  @Override
  public <T> Future<T> asyncGet(String key, Transcoder<T> tc) {
    return getCouchbaseClient().asyncGet(key, tc);
  }

  @Override
  public Future<Object> asyncGet(String key) {
    return getCouchbaseClient().asyncGet(key);
  }

  @Override
  public Future<CASValue<Object>> asyncGetAndTouch(String key, int exp) {
    return getCouchbaseClient().asyncGetAndTouch(key, exp);
  }

  @Override
  public <T> Future<CASValue<T>> asyncGetAndTouch(String key, int exp, Transcoder<T> tc) {
    return getCouchbaseClient().asyncGetAndTouch(key, exp, tc);
  }

  @Override
  public CASValue<Object> getAndTouch(String key, int exp) {
    return getCouchbaseClient().getAndTouch(key, exp);
  }

  @Override
  public <T> CASValue<T> getAndTouch(String key, int exp, Transcoder<T> tc) {
    return getCouchbaseClient().getAndTouch(key, exp, tc);
  }

  @Override
  public <T> Future<CASValue<T>> asyncGets(String key, Transcoder<T> tc) {
    return getCouchbaseClient().asyncGets(key, tc);
  }

  @Override
  public Future<CASValue<Object>> asyncGets(String key) {
    return getCouchbaseClient().asyncGets(key);
  }

  @Override
  public <T> CASValue<T> gets(String key, Transcoder<T> tc) {
    return getCouchbaseClient().gets(key, tc);
  }

  @Override
  public CASValue<Object> gets(String key) {
    return getCouchbaseClient().gets(key);
  }

  @Override
  public <T> T get(String key, Transcoder<T> tc) {
    return getCouchbaseClient().get(key, tc);
  }

  @Override
  public Object get(String key) {
    return getCouchbaseClient().get(key);
  }

  @Override
  public <T> BulkFuture<Map<String, T>> asyncGetBulk(Iterator<String> keys, Iterator<Transcoder<T>> tcs) {
    return getCouchbaseClient().asyncGetBulk(keys, tcs);
  }

  @Override
  public <T> BulkFuture<Map<String, T>> asyncGetBulk(Collection<String> keys, Iterator<Transcoder<T>> tcs) {
    return getCouchbaseClient().asyncGetBulk(keys, tcs);
  }

  @Override
  public <T> BulkFuture<Map<String, T>> asyncGetBulk(Iterator<String> keys, Transcoder<T> tc) {
    return getCouchbaseClient().asyncGetBulk(keys, tc);
  }

  @Override
  public <T> BulkFuture<Map<String, T>> asyncGetBulk(Collection<String> keys, Transcoder<T> tc) {
    return getCouchbaseClient().asyncGetBulk(keys, tc);
  }

  @Override
  public BulkFuture<Map<String, Object>> asyncGetBulk(Iterator<String> keys) {
    return getCouchbaseClient().asyncGetBulk(keys);
  }

  @Override
  public BulkFuture<Map<String, Object>> asyncGetBulk(Collection<String> keys) {
    return getCouchbaseClient().asyncGetBulk(keys);
  }

  @Override
  public <T> BulkFuture<Map<String, T>> asyncGetBulk(Transcoder<T> tc, String... keys) {
    return getCouchbaseClient().asyncGetBulk(tc, keys);
  }

  @Override
  public BulkFuture<Map<String, Object>> asyncGetBulk(String... keys) {
    return getCouchbaseClient().asyncGetBulk(keys);
  }

  @Override
  public <T> Map<String, T> getBulk(Iterator<String> keys, Transcoder<T> tc) {
    return getCouchbaseClient().getBulk(keys, tc);
  }

  @Override
  public <T> Map<String, T> getBulk(Collection<String> keys, Transcoder<T> tc) {
    return getCouchbaseClient().getBulk(keys, tc);
  }

  @Override
  public Map<String, Object> getBulk(Iterator<String> keys) {
    return getCouchbaseClient().getBulk(keys);
  }

  @Override
  public Map<String, Object> getBulk(Collection<String> keys) {
    return getCouchbaseClient().getBulk(keys);
  }

  @Override
  public <T> Map<String, T> getBulk(Transcoder<T> tc, String... keys) {
    return getCouchbaseClient().getBulk(tc, keys);
  }

  @Override
  public Map<String, Object> getBulk(String... keys) {
    return getCouchbaseClient().getBulk(keys);
  }

  @Override
  public <T> Future<Boolean> touch(String key, int exp, Transcoder<T> tc) {
    return getCouchbaseClient().touch(key, exp, tc);
  }

  @Override
  public Future<Boolean> touch(String key, int exp) {
    return getCouchbaseClient().touch(key, exp);
  }

  @Override
  public Map<SocketAddress, String> getVersions() {
    return getCouchbaseClient().getVersions();
  }

  @Override
  public Map<SocketAddress, Map<String, String>> getStats() {
    return getCouchbaseClient().getStats();
  }

  @Override
  public Map<SocketAddress, Map<String, String>> getStats(String prefix) {
    return getCouchbaseClient().getStats(prefix);
  }

  @Override
  public long incr(String key, long by) {
    return getCouchbaseClient().incr(key, by);
  }

  @Override
  public long incr(String key, int by) {
    return getCouchbaseClient().incr(key, by);
  }

  @Override
  public long decr(String key, long by) {
    return getCouchbaseClient().decr(key, by);
  }

  @Override
  public long decr(String key, int by) {
    return getCouchbaseClient().decr(key, by);
  }

  @Override
  public long incr(String key, long by, long def, int exp) {
    return getCouchbaseClient().incr(key, by, def, exp);
  }

  @Override
  public long incr(String key, int by, long def, int exp) {
    return getCouchbaseClient().incr(key, by, def, exp);
  }

  @Override
  public long decr(String key, long by, long def, int exp) {
    return getCouchbaseClient().decr(key, by, def, exp);
  }

  @Override
  public long decr(String key, int by, long def, int exp) {
    return getCouchbaseClient().decr(key, by, def, exp);
  }

  @Override
  public Future<Long> asyncIncr(String key, long by) {
    return getCouchbaseClient().asyncIncr(key, by);
  }

  @Override
  public Future<Long> asyncIncr(String key, int by) {
    return getCouchbaseClient().asyncIncr(key, by);
  }

  @Override
  public Future<Long> asyncDecr(String key, long by) {
    return getCouchbaseClient().asyncDecr(key, by);
  }

  @Override
  public Future<Long> asyncDecr(String key, int by) {
    return getCouchbaseClient().asyncDecr(key, by);
  }

  @Override
  public long incr(String key, long by, long def) {
    return getCouchbaseClient().incr(key, by, def);
  }

  @Override
  public long incr(String key, int by, long def) {
    return getCouchbaseClient().incr(key, by, def);
  }

  @Override
  public long decr(String key, long by, long def) {
    return getCouchbaseClient().decr(key, by, def);
  }

  @Override
  public long decr(String key, int by, long def) {
    return getCouchbaseClient().decr(key, by, def);
  }

  @Override
  public Future<Boolean> delete(String key) {
    return getCouchbaseClient().delete(key);
  }

  @Override
  public Future<Boolean> delete(String key, long cas) {
    return getCouchbaseClient().delete(key, cas);
  }

  @Override
  public Future<Boolean> flush(int delay) {
    return getCouchbaseClient().flush(delay);
  }

  @Override
  public Future<Boolean> flush() {
    return getCouchbaseClient().flush();
  }

  @Override
  public boolean waitForQueues(long timeout, TimeUnit unit) {
    return getCouchbaseClient().waitForQueues(timeout, unit);
  }

  @Override
  public boolean addObserver(ConnectionObserver obs) {
    return getCouchbaseClient().addObserver(obs);
  }

  @Override
  public boolean removeObserver(ConnectionObserver obs) {
    return getCouchbaseClient().removeObserver(obs);
  }

  @Override
  public Set<String> listSaslMechanisms() {
    return getCouchbaseClient().listSaslMechanisms();
  }
}
