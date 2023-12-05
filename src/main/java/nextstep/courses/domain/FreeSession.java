package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDate;

public class FreeSession extends Session {

    public FreeSession(Long id, CoverImage coverImage, LocalDate startDate, LocalDate endDate) {
        super(id, coverImage, startDate, endDate);
    }

    @Override
    public void register(Payment payment) {
        validateStatus();
        this.students.addStudent(payment.paidUser());
    }
}
