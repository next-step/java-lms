package nextstep.courses.domain;

public class MaxEnrollment {

  private final int maxEnrollmentSize;

  public MaxEnrollment(int maxEnrollmentSize) {
    this.maxEnrollmentSize = maxEnrollmentSize;
  }

  public boolean isFull(int size) {
    return maxEnrollmentSize <= size;
  }
}
