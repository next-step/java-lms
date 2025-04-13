package nextstep.payments.domain;

public class PaidEnrollmentPolicy implements EnrollmentPolicy {

    private final int maxEnrolledCount;
    private final int price;


    public PaidEnrollmentPolicy(int maxEnrolledCount, int price) {
        this.maxEnrolledCount = maxEnrolledCount;
        this.price = price;
    }

    @Override
    public boolean canEnroll(int currentEnrolledCount, Payment payment) {
        return currentEnrolledCount < maxEnrolledCount && payment.isSameAmount((long) price);
    }
}
