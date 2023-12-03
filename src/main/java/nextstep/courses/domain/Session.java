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
        this(id, coverImage, startDate, endDate, Status.NOT_OPEN, new Students());
    }

    private Session(Long id, CoverImage coverImage, LocalDate startDate, LocalDate endDate, Status status, Students students) {
        validateNotNull(id, coverImage, startDate, endDate);
        this.id = id;
        this.coverImage = coverImage;
        this.period = new Period(startDate, endDate);
        this.status = status;
        this.students = students;
    }

    private void validateNotNull(Long id, CoverImage coverImage, LocalDate startDate, LocalDate endDate) {
        if (id == null || coverImage == null || startDate == null || endDate == null) {
            throw new IllegalArgumentException("id, 커버이미지, 시작일, 종료일은 필수값입니다.");
        }
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
