package nextstep.payments.domain;

public class PaidEnrollmentPolicy implements EnrollmentPolicy {

    private final int capacity;
    private final int price;


    public PaidEnrollmentPolicy(int capacity, int price) {
        this.capacity = capacity;
        this.price = price;
    }

    @Override
    public boolean canEnroll(int currentEnrolledCount, int paidAmount) {
        return currentEnrolledCount < capacity && paidAmount == price;
    }
}
