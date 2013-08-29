package com.couchbase.client.mapping;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class QueryInfo {

  private final String key;
  private final String message;

  @JsonCreator
  public QueryInfo(
    @JsonProperty("key") String key,
    @JsonProperty("message") String message) {
    this.key = key;
    this.message = message;
  }

  public String getKey() {
    return key;
  }

  public String getMessage() {
    return message;
  }

}