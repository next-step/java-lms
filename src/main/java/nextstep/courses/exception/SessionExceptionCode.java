package nextstep.courses.exception;

import exception.ExceptionCode;

public enum SessionExceptionCode implements ExceptionCode {
  EXCEED_MAX_PERSONNEL_COUNT("강의 최대 수강 인원을 초과하였습니다."),
  ONLY_RECRUITING_STATUS_ALLOWED("강의 상태가 모집중일 때만 가능합니다."),
  ;

  private final String defaultMessage;

  SessionExceptionCode(String defaultMessage) {
    this.defaultMessage = defaultMessage;
  }

  @Override
  public String getDefaultMessage() {
    return defaultMessage;
  }
}
