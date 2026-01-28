package com.shirt.pod.exception;

import lombok.Getter;

import java.text.MessageFormat;

@Getter
public class AppException extends RuntimeException {
  private final ErrorCode errorCode;
  private final Object[] params;

  public AppException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
    this.params = new Object[0];
  }

  public AppException(ErrorCode errorCode, Object... params) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
    this.params = params;
  }

  public String getFormattedMessage() {
    if (params == null || params.length == 0) {
      return errorCode.getMessage();
    }
    return MessageFormat.format(errorCode.getMessage(), params);
  }
}
