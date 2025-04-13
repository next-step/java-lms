package nextstep.payments.domain;

public class PaidEnrollmentPolicy implements EnrollmentPolicy {

    private final int capacity;
    private final int price;


    public PaidEnrollmentPolicy(int capacity, int price) {
        this.capacity = capacity;
        this.price = price;
    }

    @Override
    public boolean canEnroll(int currentEnrolledCount, Payment payment) {
        return currentEnrolledCount < capacity && payment.isSameAmount((long) price);
    }
}
