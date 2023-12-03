package nextstep.courses.domain;

import nextstep.courses.exception.NotOpenSessionException;
import nextstep.courses.exception.OutOfSessionException;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;

public class Session {

    private final Long id;
    private final CoverImage coverImage;
    private final Period period;
    private Status status;
    protected final Students students;

    public Session(Long id, CoverImage coverImage, LocalDate startDate, LocalDate endDate) {
        this(id, coverImage, new Period(startDate, endDate), Status.NOT_OPEN, new Students());
    }

    private Session(Long id, CoverImage coverImage, Period period, Status status, Students students) {
        this.id = id;
        this.coverImage = coverImage;
        this.period = period;
        this.status = status;
        this.students = students;
    }

    public void register(Payment payment) {
        validateStatus();
        this.students.addStudent(payment.findPaidUser());
    }

    protected void validateStatus() {
        if (status != Status.OPEN) {
            throw new NotOpenSessionException();
        }
    }

    public void openSession() {
        if (!period.isDateWithinRange(LocalDate.now())) {
            throw new OutOfSessionException();
        }
        changeStatusOpen();
    }

    private void changeStatusOpen() {
        this.status = Status.OPEN;
    }
}
