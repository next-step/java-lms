package nextstep.courses.domain.session;

public class EnrollmentCount {
    private int maxEnrollmentCount;
    private int remainEnrollmentCount;

    public EnrollmentCount(final int maxEnrollmentCount) {
        this.maxEnrollmentCount = maxEnrollmentCount;
        this.remainEnrollmentCount = maxEnrollmentCount;
    }

    public EnrollmentCount(final int maxEnrollmentCount, final int remainEnrollmentCount) {
        this.maxEnrollmentCount = maxEnrollmentCount;
        this.remainEnrollmentCount = remainEnrollmentCount;
    }

    public boolean isNotRemain() {
        return remainEnrollmentCount == 0;
    }

    public void decrease() {
        this.remainEnrollmentCount--;
    }

    public int maxEnrollmentCount() {
        return maxEnrollmentCount;
    }

    public int remainEnrollmentCount() {
        return remainEnrollmentCount;
    }
}
