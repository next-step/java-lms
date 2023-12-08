package nextstep.courses.domain.session;

public class EnrollmentCount {
    private int availableCount;
    private int remainCount;

    public EnrollmentCount(final int availableCount) {
        this.availableCount = availableCount;
        this.remainCount = availableCount;
    }

    public boolean isNoRemaining() {
        return remainCount <= 0;
    }

    public void decrease() {
        this.remainCount--;
    }
}
