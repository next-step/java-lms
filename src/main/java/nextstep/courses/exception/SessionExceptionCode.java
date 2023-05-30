package nextstep.courses.exception;

import exception.ExceptionCode;

public enum SessionExceptionCode implements ExceptionCode {

  SESSION_NOT_FOUND("해당 강의는 존재하지 않습니다."),
  EXCEED_MAX_PERSONNEL_COUNT("강의 최대 수강 인원을 초과하였습니다."),
  CANNOT_ENROLL_SESSION("강의를 신청 할 수 없는 상태입니다."),
  STUDENT_ALREADY_REGISTERED("해당 강의에 이미 수강생으로 등록되어 있습니다."),
  STUDENT_NOT_FOUND("해당 강의에서는 해당 수강생이 존재하지 않습니다."),
  TEACHER_NOT_FOUND("해당 강의의 강사가 아닙니다."),
  TEACHER_CANNOT_ENROLL_SESSION("강사는 자신의 강의를 수강신청 할 수 없습니다.");

  private final String message;

  SessionExceptionCode(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
