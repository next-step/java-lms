package nextstep.courses.domain;

import nextstep.courses.exception.OverMaxStudentsException;
import nextstep.courses.exception.PaymentMismatchException;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;

public class PaidSession extends Session {

    private final int maxStudents;
    private final Long fee;

    public PaidSession(Long id, CoverImage coverImage, Status status, LocalDate startDate, LocalDate endDate, int maxStudents, Long fee) {
        super(id, coverImage, status, startDate, endDate);
        this.maxStudents = maxStudents;
        this.fee = fee;
    }

    @Override
    public void register(Payment payment) {
        validateMaxStudents();
        validatePaymentFee(payment.findAmount());
        this.students.addStudent(payment.findPaidUser());
    }

    private void validatePaymentFee(Long paymentAmount) {
        if (!this.fee.equals(paymentAmount)) {
            throw new PaymentMismatchException(fee);
        }
    }

    private void validateMaxStudents() {
        if (this.students.isRegistrationFull(maxStudents)) {
            throw new OverMaxStudentsException(maxStudents);
        }
    }
}
