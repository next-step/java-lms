package nextstep.users.exception;

import exception.ExceptionCode;

public enum UserExceptionCode implements ExceptionCode {

  USER_NOT_FOUND("해당 유저를 찾을 수 없습니다."),
  ;

  private final String message;

  UserExceptionCode(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
