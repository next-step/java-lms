package nextstep.courses.exception;

import exception.ExceptionCode;

public enum CourseExceptionCode implements ExceptionCode {

  ;

  private final String defaultMessage;

  CourseExceptionCode(String defaultMessage) {
    this.defaultMessage = defaultMessage;
  }

  @Override
  public String getDefaultMessage() {
    return defaultMessage;
  }
}
