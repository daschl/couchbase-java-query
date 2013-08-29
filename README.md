Couchbase Java Client - Querying Extension
==========================================
Where no query has relaxed before.

Introduction
------------
This is a highly experimental extension to the Couchbase Java Client, which enables the possibility of running
N1QL queries against a Couchbase cluster.

You need [tuqtng](https://github.com/couchbaselabs/tuqtng) running on port 8093. Don't know what that is? Move on
for now.

Usage
-----
Instantiate the `CouchbaseQueryClient` similar to a regular `CouchbaseClient`, but only pass through the hostnames/
ip addresses of the nodes. The client here proxies all regular operations through and provides a few new methods.

More examples soon here, look through the tests if you are curious.

Todo
----
Before a 1.0 milestone is reached, the following things need to be implemented (informal list):
- Testing and Handling of errors (socket closes, 500ers, 404ers,...)
- Full API documentation
- Overhaul this README with examples and usages
- Unit-Tests of the netty codec
- Unit-Tests of the mapping POJOs
- Test the correct proxying of methods
- make unit test env more flexible (variable host,...)
- maybe also add a streaming api for responses once the other stuff is done and reasonably stable.