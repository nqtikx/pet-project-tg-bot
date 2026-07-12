package com.tgbot.prodaction.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

@JsonPropertyOrder({"status", "error", "message", "path", "timestamp"})
public class ErrorResponse {
  private Integer status;
  private String error;
  private String message;
  private String path;
  private LocalDateTime timestamp;

  public ErrorResponse(HttpStatus status, String message, String path) {
    this.status = status.value();
    this.error = status.getReasonPhrase();
    this.message = message;
    this.path = path;
    this.timestamp = LocalDateTime.now();
  }

  public ErrorResponse() {
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
