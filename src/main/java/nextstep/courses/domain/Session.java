package nextstep.courses.domain;

import nextstep.courses.exception.InvalidSessionException;
import nextstep.courses.exception.NotOpenSessionException;
import nextstep.courses.exception.OutOfSessionException;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Session extends BaseEntity {

    private final Long id;
    private final CoverImage coverImage;
    private final Period period;
    private Status status;
    protected final Students students;

    public Session(Long id, CoverImage coverImage, LocalDate startDate, LocalDate endDate) {
        this(id, coverImage, new Period(startDate, endDate), Status.NOT_OPEN, new Students(), LocalDateTime.now(), null);
    }

    private Session(Long id, CoverImage coverImage, Period period, Status status, Students students, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        validateNotNull(id, coverImage, period);
        this.id = id;
        this.coverImage = coverImage;
        this.period = period;
        this.status = status;
        this.students = students;
    }

    private void validateNotNull(Long id, CoverImage coverImage, Period period) {
        if (id == null || coverImage == null || period == null) {
            throw new InvalidSessionException();
        }
    }

    public void register(Payment payment) {
        validateStatus();
        this.students.addStudent(payment.paidUser());
    }

    protected void validateStatus() {
        if (!status.isOpen()) {
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
        this.status = status.ofOpen();
    }
}
