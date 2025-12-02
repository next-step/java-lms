package nextstep.courses.domain;

public class SessionEnrollment {
  private final int maxCapacity;
  private final int tuitionFee;
  private final int studentCount;

  public static SessionEnrollment free() {
    return new SessionEnrollment(Integer.MAX_VALUE, 0, 0);
  }

  public static SessionEnrollment paid(int maxCapacity, int tuitionFee) {
    return new SessionEnrollment(maxCapacity, tuitionFee, 0);
  }

  public SessionEnrollment(int maxCapacity, int tuitionFee, int studentCount) {
    validateCapacity(maxCapacity, studentCount);
    this.maxCapacity = maxCapacity;
    this.tuitionFee = tuitionFee;
    this.studentCount = studentCount;
  }

  public SessionEnrollment enroll(int payAmount) {
    validateCapacity(maxCapacity, studentCount + 1);
    validateTuitionFee(payAmount);
    return new SessionEnrollment(maxCapacity, tuitionFee, studentCount + 1);
  }

  public SessionEnrollment enroll() {
    return enroll(0);
  }

  private void validateTuitionFee(int payAmount) {
    if (payAmount != tuitionFee) {
      throw new IllegalArgumentException("수강료와 지불한 금액이 정확히 일치해야 합니다.");
    }
  }

  private void validateCapacity(int maxCapacity, int studentCount) {
    if (maxCapacity < studentCount) {
      throw new IllegalArgumentException("최대 수강 인원을 초과할 수 없습니다.");
    }
  }
}
