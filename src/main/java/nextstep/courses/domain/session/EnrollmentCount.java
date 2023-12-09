package nextstep.courses.domain.session;

public class EnrollmentCount {
    private final int availableCount;
    private int remainCount;

    public EnrollmentCount(final int availableCount) {
        this.availableCount = availableCount;
        this.remainCount = availableCount;
    }

    public EnrollmentCount(final int maxEnrollmentCount, final int remainEnrollmentCount) {
        this.availableCount = maxEnrollmentCount;
        this.remainCount = remainEnrollmentCount;
    }

    public boolean isNoRemaining() {
        return remainCount <= 0;
    }

    public void decrease() {
        this.remainCount--;
    }

    public int getAvailableCount() {
        return availableCount;
    }

    public int getRemainCount() {
        return remainCount;
    }
}
