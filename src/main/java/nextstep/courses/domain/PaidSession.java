package nextstep.courses.domain;

import nextstep.courses.exception.OverMaxStudentsException;
import nextstep.courses.exception.PaymentMismatchException;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PaidSession extends Session {

    private final int maxStudents;
    private final Long fee;

    public PaidSession(Long id, CoverImage coverImage, LocalDate startDate, LocalDate endDate, int maxStudents, Long fee) {
        super(id, SessionType.PAID, coverImage, startDate, endDate);
        this.maxStudents = maxStudents;
        this.fee = fee;
    }

    public PaidSession(Long id, SessionType type, CoverImage coverImage, LocalDate startDate, LocalDate endDate, int maxStudents, Long fee, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, type, coverImage, startDate, endDate, createdAt, updatedAt);
        this.maxStudents = maxStudents;
        this.fee = fee;
    }

    @Override
    public void register(Payment payment) {
        validateMaxStudents();
        validatePaymentFee(payment);
        validateStatus();
        this.students.addStudent(payment.paidUser());
    }

    private void validateMaxStudents() {
        if (this.students.isRegistrationFull(maxStudents)) {
            throw new OverMaxStudentsException(maxStudents);
        }
    }

    private void validatePaymentFee(Payment payment) {
        if (!payment.equalAmount(fee)) {
            throw new PaymentMismatchException(fee);
        }
    }

    public int maxStudents() {
        return maxStudents;
    }

    public Long fee() {
        return fee;
    }
}
