package nextstep.courses.domain;

public class AttendeeCount {

    private final Amount maxCount;
    private final Amount currentCount;

    public AttendeeCount(int maxCount) {
        this(new Amount(maxCount), new Amount(0));
    }

    public AttendeeCount(int maxCount, int currentCount) {
        this(new Amount(maxCount), new Amount(currentCount));
    }

    public AttendeeCount(Amount maxCount, Amount currentCount) {
        this.maxCount = maxCount;
        this.currentCount = currentCount;
    }

    public boolean canSignUp() {
        return maxCount.isBigger(currentCount);
    }

    public boolean limitExist() {
        return maxCount.exist();
    }
}
