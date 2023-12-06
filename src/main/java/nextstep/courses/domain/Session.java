package nextstep.courses.domain;

import nextstep.courses.exception.InvalidSessionException;
import nextstep.courses.exception.NotOpenSessionException;
import nextstep.courses.exception.OutOfSessionException;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Session extends BaseEntity {

    private final Long id;
    private final SessionType type;
    private final CoverImage coverImage;
    private final Period period;
    private Status status;
    protected final Students students;

    public Session(Long id, SessionType type, CoverImage coverImage, LocalDate startDate, LocalDate endDate) {
        this(id, type, coverImage, new Period(startDate, endDate), Status.NOT_OPEN, new Students(), LocalDateTime.now(), null);
    }

    public Session(Long id, SessionType type, CoverImage coverImage, LocalDate startDate, LocalDate endDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, type, coverImage, new Period(startDate, endDate), Status.NOT_OPEN, new Students(), createdAt, updatedAt);
    }

    private Session(Long id, SessionType type, CoverImage coverImage, Period period, Status status, Students students, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        validateNotNull(id, coverImage, period);
        this.id = id;
        this.type = type;
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

    public abstract void register(Payment payment);

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

    public Long id() {
        return id;
    }

    public Long ImageId() {
        return coverImage.getId();
    }

    public String type() {
        return type.code();
    }

    public String status() {
        return status.name();
    }

    public LocalDate startDate() {
        return period.startDate();
    }

    public LocalDate endDate() {
        return period.endDate();
    }

}
