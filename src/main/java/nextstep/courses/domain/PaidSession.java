package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaidSession implements SessionStrategy {

    private int maxEnrollmentCount;
    private int currentEnrollmentCount;
    private int tuitionFee;

    private List<Payment> payments;

    public PaidSession(int maxEnrollmentCount, int currentEnrollmentCount, int tuitionFee){
        this.maxEnrollmentCount = maxEnrollmentCount;
        this.currentEnrollmentCount = currentEnrollmentCount;
        this.tuitionFee = tuitionFee;
        this.payments = new ArrayList<>();
    }

    private boolean isFull() {
        return maxEnrollmentCount <= currentEnrollmentCount;
    }

    @Override
    public boolean canEnroll(Payment payment) {
        return !isFull() && payment.isTuitionPaid(tuitionFee); //todo
    }

    public void enroll(Payment payment){
        if(canEnroll(payment)){
            payments.add(payment);
        }
    }
}
