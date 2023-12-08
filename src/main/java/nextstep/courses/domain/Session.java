package nextstep.courses.domain;

import nextstep.courses.exception.InvalidSessionException;
import nextstep.courses.exception.NotOpenSessionException;
import nextstep.courses.exception.OutOfSessionException;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Session extends BaseEntity {

    private final Long id;
    private final SessionType type;
    private final CoverImage coverImage;
    private final Period period;
    private Status status;
    private final Students students;
    private final PaidCondition paidCondition;

    public static Session ofFree(Long id, CoverImage coverImage, LocalDate startDate, LocalDate endDate) {
        return new Session(id, SessionType.FREE, coverImage, new Period(startDate, endDate), Status.NOT_OPEN, 0, 0L, LocalDateTime.now(), null);
    }

    public static Session ofPaid(Long id, CoverImage coverImage, LocalDate startDate, LocalDate endDate, int maxStudents, Long fee) {
        return new Session(id, SessionType.PAID, coverImage, new Period(startDate, endDate), Status.NOT_OPEN, maxStudents, fee, LocalDateTime.now(), null);
    }

    public static Session of(Long id, SessionType type, CoverImage coverImage, Status status, LocalDate startDate, LocalDate endDate, int maxStudents, Long fee, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Session(id, type, coverImage, new Period(startDate, endDate), status, maxStudents, fee, createdAt, updatedAt);
    }

    private Session(Long id, SessionType type, CoverImage coverImage, Period period, Status status, int maxStudents, Long fee, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        validateNotNull(id, coverImage, period);
        this.id = id;
        this.type = type;
        this.coverImage = coverImage;
        this.period = period;
        this.status = status;
        this.students = new Students();
        this.paidCondition = new PaidCondition(maxStudents, fee);
    }

    private void validateNotNull(Long id, CoverImage coverImage, Period period) {
        if (id == null || coverImage == null || period == null) {
            throw new InvalidSessionException();
        }
    }

    public void register(Payment payment) {
        validateStatus();
        if (type.isPaid()) {
            paidCondition.validate(this.students, payment);
        }
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

    public Long id() {
        return id;
    }

    public Long imageId() {
        return coverImage.getId();
    }

    public String type() {
        return type.name();
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

    public int maxStudents() {
        return this.paidCondition.maxStudents();
    }

    public Long fee() {
        return this.paidCondition.fee();
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", type=" + type +
                ", coverImage=" + coverImage +
                ", period=" + period +
                ", status=" + status +
                ", students=" + students +
                ", paidCondition=" + paidCondition +
                '}';
    }
}
