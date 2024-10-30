package nextstep.courses.domain;

public class PaidSession implements SessionStrategy {

    private int maxEnrollmentCount;
    private int currentEnrollmentCount;
    private int tuitionFee;

    private int paymentAmount; //todo

    public PaidSession(int maxEnrollmentCount, int currentEnrollmentCount, int tuitionFee, int paymentAmount){
        this.maxEnrollmentCount = maxEnrollmentCount;
        this.currentEnrollmentCount = currentEnrollmentCount;
        this.tuitionFee = tuitionFee;
        this.paymentAmount = paymentAmount;
    }

    private boolean isFull() {
        return maxEnrollmentCount <= currentEnrollmentCount;
    }
    private boolean isTuitionPaid() {
        return tuitionFee == paymentAmount;
    }

    @Override
    public boolean canEnroll() {
        return !isFull() && isTuitionPaid(); //todo
    }
}
