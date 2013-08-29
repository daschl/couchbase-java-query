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
