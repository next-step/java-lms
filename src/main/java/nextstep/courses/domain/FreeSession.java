package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FreeSession extends Session {

    public FreeSession(Long id, CoverImage coverImage, LocalDate startDate, LocalDate endDate) {
        this(id, SessionType.FREE, coverImage, startDate, endDate);
    }

    public FreeSession(Long id, SessionType type, CoverImage coverImage, LocalDate startDate, LocalDate endDate) {
        super(id, type, coverImage, startDate, endDate);
    }

    public FreeSession(Long id, SessionType type, CoverImage coverImage, LocalDate startDate, LocalDate endDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, type, coverImage, startDate, endDate, createdAt, updatedAt);
    }

    @Override
    public void register(Payment payment) {
        validateStatus();
        this.students.addStudent(payment.paidUser());
    }
}
