package com.metaship.edu.openapi.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final Integer code;
  private final String message;
  private final HttpStatus httpStatus;

  public CustomException(Integer code, String message, HttpStatus httpStatus) {
    this.code = code;
    this.message = message;
    this.httpStatus = httpStatus;
  }

  public CustomException(String message, HttpStatus httpStatus) {
    this(0, message, httpStatus);
  }

  @Override
  public String getMessage() {
    return message;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public Integer getCode() {
    return code;
  }

}
