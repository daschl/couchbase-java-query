Couchbase Java Client - Querying Extension
==========================================
Where no query has relaxed before.

Introduction
------------
This is a highly experimental extension to the Couchbase Java Client, which enables the possibility of running
N1QL queries against a Couchbase cluster.

You need [tuqtng](https://github.com/couchbaselabs/tuqtng) running on port 8093 and point it to your cluster.

Usage
-----
Instantiate the `CouchbaseQueryClient` similar to a regular `CouchbaseClient`, but only pass through the hostnames/
ip addresses of the nodes. The client here proxies all regular operations through and provides a few more methods.

A simple example, which assumes both tuqtng and couchbase are running on localhost and you have the `beer-sample`
bucket imported:

```java
List<String> nodes = new ArrayList<String>();
nodes.add("127.0.0.1");

CouchbaseQueryClient client = new CouchbaseQueryClient(nodes, "beer-sample", "");

QueryResult query = client.query("SELECT * FROM beer-sample LIMIT 1");
System.out.println("Success: " + query.isSuccess());

client.shutdown();
```

If you want to have tuqtng and couchbase running on different machines, use the constructor which allows you to pass in
two distinct lists for those (see API docs). Note that this library proxies all `CouchbaseClient` operations through!

The `QueryResult` object contains different properties based on the result of the query. Here is a more sophisticated
example which checks on the return type and gives you some info:

```java
CouchbaseQueryClient client = new CouchbaseQueryClient(Arrays.asList("127.0.0.1"), "beer-sample", "");

QueryResult query = client.query("SELECT * FROM beer-sample LIMIT 1");
if (query.isSuccess()) {
  System.out.println("Got " + query.getResult().size() + " rows to iterate").
  for (QueryRow row : query.getResult()) {
    row.get("mykey"); //... adapt to your responses
  }
} else {
  System.out.println("Something went wrong: " + query.getError().getMessage());
}
client.shutdown();
```

The enclosed `QueryRows` are basically `HashMap`s, so you can iterate and access them with a familiar pattern. There is
also more information transferred with the query, the API will be extended to make use for it in a type-safe manner.

If you want to work with the future, use the `asyncQuery(String)` method as before. This returns a `HttpFuture` on which
you can react.

Finally, you can use the `{bucket}` key in your query to let it automatically replace it with the bucket connected. So,
the query `SELECT * FROM {bucket}` will be translated into `SELECT * FROM beer-sample` if you are connected to the
`beer-sample` bucket. That way, you can abstract the query from the actual containing bucket and reuse the query across
more buckets (testing, production...).

Todo
----
Before a 1.0 milestone is reached, the following things need to be implemented (informal list):
- Testing and Handling of errors (socket closes, 500ers, 404ers,...)
- Full API documentation
- Unit-Tests of the netty codec
- Unit-Tests of the mapping POJOs
- Test the correct proxying of methods
- make unit test env more flexible (variable host,...)
- maybe also add a streaming api for responses once the other stuff is done and reasonably stable.
- Provide a building mechanism to customize settings and also use a factory for the couchbase client.
- Upgrade to 1.2 once released so we can use the callbacks and add metrics.