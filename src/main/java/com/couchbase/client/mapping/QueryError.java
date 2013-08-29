package com.couchbase.client.mapping;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class QueryError {

  private final String cause;
  private final int code;
  private final String key;
  private final String message;

  @JsonCreator
  public QueryError(
    @JsonProperty("cause") String cause,
    @JsonProperty("code") int code,
    @JsonProperty("key") String key,
    @JsonProperty("message") String message) {
    this.cause = cause;
    this.code = code;
    this.key = key;
    this.message = message;
  }

  public String getCause() {
    return cause;
  }

  public int getCode() {
    return code;
  }

  public String getKey() {
    return key;
  }

  public String getMessage() {
    return message;
  }

}
