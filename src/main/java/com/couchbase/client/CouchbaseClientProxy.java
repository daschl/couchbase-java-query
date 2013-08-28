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
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> OperationFuture<Boolean> asyncUnlock(String key, long casId, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> asyncUnlock(String key, long casId) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> Boolean unlock(String key, long casId, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Boolean unlock(String key, long casId) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Map<MemcachedNode, ObserveResponse> observe(String key, long cas) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void observePoll(String key, long cas, PersistTo persist, ReplicateTo replicate, boolean isDelete) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> set(String key, Object value) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> set(String key, int exp, Object value, PersistTo persist) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> set(String key, Object value, PersistTo persist) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> set(String key, int exp, Object value, ReplicateTo replicate) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> set(String key, Object value, ReplicateTo replicate) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> set(String key, int exp, Object value, PersistTo persist, ReplicateTo replicate) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> set(String key, Object value, PersistTo persist, ReplicateTo replicate) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> add(String key, Object value) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> add(String key, int exp, Object value, PersistTo persist) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> add(String key, Object value, PersistTo persist) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> add(String key, int exp, Object value, ReplicateTo replicate) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> add(String key, Object value, ReplicateTo replicate) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> add(String key, int exp, Object value, PersistTo persist, ReplicateTo replicate) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> add(String key, Object value, PersistTo persist, ReplicateTo replicate) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> replace(String key, Object value) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> replace(String key, int exp, Object value, PersistTo persist) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> replace(String key, Object value, PersistTo persist) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> replace(String key, int exp, Object value, ReplicateTo replicate) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> replace(String key, Object value, ReplicateTo replicate) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> replace(String key, int exp, Object value, PersistTo persist, ReplicateTo replicate) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> replace(String key, Object value, PersistTo persist, ReplicateTo replicate) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public CASResponse cas(String key, long cas, Object value, PersistTo req, ReplicateTo rep) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public CASResponse cas(String key, long cas, Object value, PersistTo req) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public CASResponse cas(String key, long cas, Object value, ReplicateTo rep) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> delete(String key, PersistTo persist) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> delete(String key, PersistTo persist, ReplicateTo replicate) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Boolean> delete(String key, ReplicateTo replicate) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public int getNumVBuckets() {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public HttpFuture<Boolean> asyncCreateDesignDoc(DesignDocument doc) throws UnsupportedEncodingException {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public HttpFuture<Boolean> asyncCreateDesignDoc(String name, String value) throws UnsupportedEncodingException {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public HttpFuture<Boolean> asyncDeleteDesignDoc(String name) throws UnsupportedEncodingException {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public HttpFuture<DesignDocument> asyncGetDesignDocument(String designDocumentName) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Boolean createDesignDoc(DesignDocument doc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Boolean deleteDesignDoc(String name) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public DesignDocument getDesignDocument(String designDocumentName) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Object getFromReplica(String key) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> T getFromReplica(String key, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public ReplicaGetFuture<Object> asyncGetFromReplica(String key) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> ReplicaGetFuture<T> asyncGetFromReplica(String key, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public HttpFuture<View> asyncGetView(String designDocumentName, String viewName) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public HttpFuture<SpatialView> asyncGetSpatialView(String designDocumentName, String viewName) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public HttpFuture<ViewResponse> asyncQuery(AbstractView view, Query query) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public ViewResponse query(AbstractView view, Query query) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Paginator paginatedQuery(View view, Query query, int docsPerPage) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public View getView(String designDocumentName, String viewName) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public SpatialView getSpatialView(String designDocumentName, String viewName) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public OperationFuture<Map<String, String>> getKeyStats(String key) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Collection<SocketAddress> getAvailableServers() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Collection<SocketAddress> getUnavailableServers() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Transcoder<Object> getTranscoder() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public NodeLocator getNodeLocator() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Future<Boolean> append(long cas, String key, Object val) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Future<Boolean> append(String key, Object val) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> Future<Boolean> append(long cas, String key, T val, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> Future<Boolean> append(String key, T val, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Future<Boolean> prepend(long cas, String key, Object val) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Future<Boolean> prepend(String key, Object val) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> Future<Boolean> prepend(long cas, String key, T val, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> Future<Boolean> prepend(String key, T val, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> Future<CASResponse> asyncCAS(String key, long casId, T value, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Future<CASResponse> asyncCAS(String key, long casId, Object value) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> CASResponse cas(String key, long casId, int exp, T value, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public CASResponse cas(String key, long casId, Object value) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> Future<Boolean> add(String key, int exp, T o, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Future<Boolean> add(String key, int exp, Object o) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> Future<Boolean> set(String key, int exp, T o, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Future<Boolean> set(String key, int exp, Object o) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> Future<Boolean> replace(String key, int exp, T o, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Future<Boolean> replace(String key, int exp, Object o) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> Future<T> asyncGet(String key, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Future<Object> asyncGet(String key) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Future<CASValue<Object>> asyncGetAndTouch(String key, int exp) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> Future<CASValue<T>> asyncGetAndTouch(String key, int exp, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public CASValue<Object> getAndTouch(String key, int exp) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> CASValue<T> getAndTouch(String key, int exp, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> Future<CASValue<T>> asyncGets(String key, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Future<CASValue<Object>> asyncGets(String key) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> CASValue<T> gets(String key, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public CASValue<Object> gets(String key) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> T get(String key, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Object get(String key) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> BulkFuture<Map<String, T>> asyncGetBulk(Iterator<String> keys, Iterator<Transcoder<T>> tcs) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> BulkFuture<Map<String, T>> asyncGetBulk(Collection<String> keys, Iterator<Transcoder<T>> tcs) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> BulkFuture<Map<String, T>> asyncGetBulk(Iterator<String> keys, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> BulkFuture<Map<String, T>> asyncGetBulk(Collection<String> keys, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public BulkFuture<Map<String, Object>> asyncGetBulk(Iterator<String> keys) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public BulkFuture<Map<String, Object>> asyncGetBulk(Collection<String> keys) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> BulkFuture<Map<String, T>> asyncGetBulk(Transcoder<T> tc, String... keys) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public BulkFuture<Map<String, Object>> asyncGetBulk(String... keys) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> Map<String, T> getBulk(Iterator<String> keys, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> Map<String, T> getBulk(Collection<String> keys, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Map<String, Object> getBulk(Iterator<String> keys) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Map<String, Object> getBulk(Collection<String> keys) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> Map<String, T> getBulk(Transcoder<T> tc, String... keys) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Map<String, Object> getBulk(String... keys) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> Future<Boolean> touch(String key, int exp, Transcoder<T> tc) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T> Future<Boolean> touch(String key, int exp) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Map<SocketAddress, String> getVersions() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Map<SocketAddress, Map<String, String>> getStats() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Map<SocketAddress, Map<String, String>> getStats(String prefix) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public long incr(String key, long by) {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public long incr(String key, int by) {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public long decr(String key, long by) {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public long decr(String key, int by) {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public long incr(String key, long by, long def, int exp) {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public long incr(String key, int by, long def, int exp) {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public long decr(String key, long by, long def, int exp) {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public long decr(String key, int by, long def, int exp) {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Future<Long> asyncIncr(String key, long by) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Future<Long> asyncIncr(String key, int by) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Future<Long> asyncDecr(String key, long by) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Future<Long> asyncDecr(String key, int by) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public long incr(String key, long by, long def) {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public long incr(String key, int by, long def) {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public long decr(String key, long by, long def) {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public long decr(String key, int by, long def) {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Future<Boolean> delete(String key) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Future<Boolean> delete(String key, long cas) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Future<Boolean> flush(int delay) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Future<Boolean> flush() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void shutdown() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean shutdown(long timeout, TimeUnit unit) {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean waitForQueues(long timeout, TimeUnit unit) {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean addObserver(ConnectionObserver obs) {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean removeObserver(ConnectionObserver obs) {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Set<String> listSaslMechanisms() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}
