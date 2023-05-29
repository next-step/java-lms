package nextstep.courses.exception;

import exception.ExceptionCode;

public enum SessionExceptionCode implements ExceptionCode {

  SESSION_NOT_FOUND("해당 강의는 존재하지 않습니다."),
  EXCEED_MAX_PERSONNEL_COUNT("강의 최대 수강 인원을 초과하였습니다."),
  ONLY_RECRUITING_STATUS_ALLOWED("강의 상태가 모집중일 때만 가능합니다."),
  STUDENT_ALREADY_REGISTERED("해당 강의에 이미 수강생으로 등록되어 있습니다."),
  ;

  private final String message;

  SessionExceptionCode(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
