package nextstep.courses.domain.session;

import java.util.Objects;

public class SessionEnrollmentInfo {

  private static final int MIN_STUDENT_COUNT = 1;
  private static final int MIN_AMOUNT = 0;
  public static final String INVALID_STUDENT_COUNT = "최소 수강 신청 인원을 넘지 못했습니다. input: %s";
  public static final String INVALID_SESSION_AMOUNT = "강의 금액이 올바르지 않습니다. input: %s";
  public static final String STUDENT_COUNT_IS_FULL = "수강 신청 가능한 인원이 모두 꽉찼습니다.";

  private SessionType sessionType;
  private int sessionAmount;
  private int studentMaxCount = Integer.MAX_VALUE;
  private int totalStudentCount = 0;

  public SessionEnrollmentInfo(SessionType sessionType, int sessionAmount) {
    valid(sessionType, sessionAmount, studentMaxCount);
    this.sessionType = sessionType;
    this.sessionAmount = sessionAmount;
  }

  public SessionEnrollmentInfo(SessionType sessionType, int sessionAmount, int studentMaxCount) {
    valid(sessionType, sessionAmount, studentMaxCount);
    this.sessionType = sessionType;
    this.sessionAmount = sessionAmount;
    this.studentMaxCount = studentMaxCount;
  }

  private void valid(SessionType sessionType, int sessionAmount, Integer studentMaxCount) {
    if (!sessionType.isFree() && studentMaxCount < MIN_STUDENT_COUNT) {
      throw new IllegalArgumentException(String.format(INVALID_STUDENT_COUNT, studentMaxCount));
    }

    if (!sessionType.isFree() && sessionAmount <= MIN_AMOUNT) {
      throw new IllegalArgumentException(String.format(INVALID_SESSION_AMOUNT, sessionAmount));
    }
  }

  public boolean isFree() {
    return sessionType.isFree();
  }

  public int getSessionAmount() {
    return sessionAmount;
  }

  public Integer getStudentMaxCount() {
    return studentMaxCount;
  }

  public Integer getTotalStudentCount() {
    return totalStudentCount;
  }

  public void addStudentCount() {
    if (sessionType.isFree()) {
      totalStudentCount++;
      return;
    }

    if (Objects.equals(totalStudentCount, studentMaxCount)) {
      throw new IllegalArgumentException(STUDENT_COUNT_IS_FULL);
    }

    totalStudentCount++;
  }
}
