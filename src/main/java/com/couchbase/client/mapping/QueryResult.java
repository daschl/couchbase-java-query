package com.couchbase.client.mapping;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Represents a result of a Query.
 */
public final class QueryResult {

  /**
   * Contains a list of {@link QueryRow}s.
   */
  private List<QueryRow> resultSet;

  /**
   * Contains a list of infos if populated.
   */
  private List<QueryInfo> info;

  /**
   * Contains the error if populated.
   */
  private QueryError error;

  /**
   * Create a new {@link QueryResult}.
   *
   * @param resultSet
   */
  @JsonCreator
  public QueryResult(
    @JsonProperty("resultset") List<QueryRow> resultSet,
    @JsonProperty("info") List<QueryInfo> info,
    @JsonProperty("error") QueryError error) {
    this.resultSet = resultSet;
    this.info = info;
    this.error = error;
  }

  public List<QueryRow> getResult() {
    return resultSet;
  }

  public boolean hasError() {
    return error != null;
  }

  public QueryError getCause() {
    return error;
  }

  public boolean isSuccess() {
    return error == null;
  }

}
