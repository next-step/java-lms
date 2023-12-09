package nextstep.courses.domain;

import nextstep.courses.exception.NegativePaidConditionException;
import nextstep.courses.exception.OverMaxStudentsException;
import nextstep.courses.exception.PaymentMismatchException;
import nextstep.payments.domain.Payment;

public class PaidCondition {

    private final int maxStudents;
    private final Long fee;

    public PaidCondition(int maxStudents, Long fee) {
        validate(maxStudents, fee);
        this.maxStudents = maxStudents;
        this.fee = fee;
    }

    private void validate(int maxStudents, Long fee) {
        if (maxStudents < 0 || fee < 0) {
            throw new NegativePaidConditionException(maxStudents, fee);
        }
    }

    public void validate(Students students, Payment payment) {
        validateMaxStudents(students);
        validatePaymentFee(payment);
    }

    private void validateMaxStudents(Students students) {
        if (students.isRegistrationFull(maxStudents)) {
            throw new OverMaxStudentsException(maxStudents);
        }
    }

    private void validatePaymentFee(Payment payment) {
        if (!payment.equalAmount(fee)) {
            throw new PaymentMismatchException(fee);
        }
    }

    public int maxStudents() {
        return this.maxStudents;
    }

    public Long fee() {
        return this.fee;
    }

    @Override
    public String toString() {
        return "PaidCondition{" +
                "maxStudents=" + maxStudents +
                ", fee=" + fee +
                '}';
    }
}
