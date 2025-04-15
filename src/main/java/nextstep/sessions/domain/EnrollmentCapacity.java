package nextstep.sessions.domain;

public class EnrollmentCapacity {
    private int maxEnrollment;
    private int currentEnrollment;

    public EnrollmentCapacity(int maxEnrollment) {
        this(maxEnrollment, 0);
    }

    public EnrollmentCapacity(int maxEnrollment, int currentEnrollment) {
        this.maxEnrollment = maxEnrollment;
        this.currentEnrollment = currentEnrollment;
    }

    public boolean isFull() {
        return currentEnrollment >= maxEnrollment;
    }

    public void increaseEnrollment() {
        validate();
        currentEnrollment++;
    }

    private void validate() {
        if (isFull()) {
            throw new IllegalStateException("정원이 초과되었습니다.");
        }
    }
}
