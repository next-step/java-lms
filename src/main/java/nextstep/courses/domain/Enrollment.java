package nextstep.courses.domain;

public class Enrollment {
    private final int maxEnrollment;
    private int currentEnrollment;

    public Enrollment(int maxEnrollment) {
        validateMaxEnrollment(maxEnrollment);
        this.maxEnrollment = maxEnrollment;
        this.currentEnrollment = 0;
    }

    private void validateMaxEnrollment(int maxEnrollment) {
        if (maxEnrollment < 0) {
            throw new IllegalArgumentException("최대 수강 인원은 0보다 커야 합니다.");
        }
    }

    public void enroll() {
        if (currentEnrollment >= maxEnrollment) {
            throw new IllegalStateException("최대 수강 인원을 초과했습니다.");
        }
        currentEnrollment++;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public int getCurrentEnrollment() {
        return currentEnrollment;
    }
} 