package nextstep.courses.domain.session;

public class EnrollmentCount {
    private int availableCount;

    public EnrollmentCount(final int availableCount) {
        this.availableCount = availableCount;
    }

    public boolean isNoRemaining() {
        return availableCount < 1;
    }

    public void decrease() {
        this.availableCount--;
    }

    public int getAvailableCount() {
        return availableCount;
    }
}
