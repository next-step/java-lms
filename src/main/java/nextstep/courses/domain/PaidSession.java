package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class PaidSession implements SessionStrategy {

    private int maxEnrollmentCount;
    private int currentEnrollmentCount;
    private int tuitionFee;

    private Payments payments;

    public PaidSession(int maxEnrollmentCount, int currentEnrollmentCount, int tuitionFee){
        this.maxEnrollmentCount = maxEnrollmentCount;
        this.currentEnrollmentCount = currentEnrollmentCount;
        this.tuitionFee = tuitionFee;
        this.payments = new Payments();
    }

    private boolean isFull() {
        return maxEnrollmentCount <= currentEnrollmentCount;
    }

    public void enroll(Payment payment){
        if(canEnroll(payment)){
            payments.add(payment);
        }
    }

    @Override
    public boolean canEnroll(Payment payment) {
        return !isFull() && payment.isTuitionPaid(tuitionFee);
    }
}
