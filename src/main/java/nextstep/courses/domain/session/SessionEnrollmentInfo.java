package nextstep.courses.domain.session;

import java.util.Objects;

public class SessionEnrollmentInfo {

  private static final int MIN_STUDENT_COUNT = 1;
  private static final int MIN_AMOUNT = 0;
  public static final String INVALID_STUDENT_COUNT = "최소 수강 신청 인원을 넘지 못했습니다. input: %s";
  public static final String INVALID_SESSION_AMOUNT = "강의 금액이 올바르지 않습니다. input: %s";
  public static final String STUDENT_COUNT_IS_FULL = "수강 신청 가능한 인원이 모두 꽉찼습니다.";

  private boolean isFree;
  private Long sessionAmount;
  private Integer studentMaxCount;
  private Integer totalStudentCount = 0;

  public SessionEnrollmentInfo(boolean isFree, Long sessionAmount, Integer studentMaxCount) {
    valid(isFree, sessionAmount, studentMaxCount);
    this.isFree = isFree;
    this.sessionAmount = sessionAmount;
    this.studentMaxCount = studentMaxCount;
  }

  public boolean valid(boolean isFree, Long sessionAmount, Integer studentMaxCount) {
    if (!isFree && studentMaxCount < MIN_STUDENT_COUNT) {
      throw new IllegalArgumentException(String.format(INVALID_STUDENT_COUNT, studentMaxCount));
    }

    if (!isFree && sessionAmount <= MIN_AMOUNT) {
      throw new IllegalArgumentException(String.format(INVALID_SESSION_AMOUNT, sessionAmount));
    }
    return true;
  }

  public boolean isFree() {
    return isFree;
  }

  public Long getSessionAmount() {
    return sessionAmount;
  }

  public Integer getStudentMaxCount() {
    return studentMaxCount;
  }

  public void addStudentCount() {
    if (isFree) {
      return;
    }

    if (Objects.equals(totalStudentCount, studentMaxCount)) {
      throw new IllegalArgumentException(STUDENT_COUNT_IS_FULL);
    }

    totalStudentCount++;
  }
}
