package nextstep.courses.domain;

import nextstep.courses.exception.InvalidSessionException;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Session extends BaseEntity {

    private final Long id;
    private final Long courseId;
    private final SessionType type;
    private final CoverImages coverImages;
    private final Period period;
    private final Status status;
    private final Students students;
    private final PaidCondition paidCondition;

    public static Session ofFree(Long id, Long courseId, CoverImages coverImages, LocalDate startDate, LocalDate endDate) {
        return new Session(id, courseId, SessionType.FREE, coverImages, new Period(startDate, endDate), new Status(LocalDate.now(), startDate, endDate), 0, 0L, LocalDateTime.now(), null);
    }

    public static Session ofPaid(Long id, Long courseId, CoverImages coverImages, LocalDate startDate, LocalDate endDate, int maxStudents, Long fee) {
        return new Session(id, courseId, SessionType.PAID, coverImages, new Period(startDate, endDate), new Status(LocalDate.now(), startDate, endDate), maxStudents, fee, LocalDateTime.now(), null);
    }

    public static Session of(Long id, Long courseId, SessionType type, CoverImages coverImages, RecruitmentStatus recruitmentStatus, LocalDate startDate, LocalDate endDate, int maxStudents, Long fee, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Session(id, courseId, type, coverImages, new Period(startDate, endDate), new Status(LocalDate.now(), startDate, endDate, recruitmentStatus), maxStudents, fee, createdAt, updatedAt);
    }

    private Session(Long id, Long courseId, SessionType type, CoverImages coverImages, Period period, Status status, int maxStudents, Long fee, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        validateNotNull(id, coverImages, period);
        this.id = id;
        this.courseId = courseId;
        this.type = type;
        this.coverImages = coverImages;
        this.period = period;
        this.status = status;
        this.students = new Students();
        this.paidCondition = new PaidCondition(maxStudents, fee);
    }

    private void validateNotNull(Long id, CoverImages coverImages, Period period) {
        if (id == null || coverImages == null || period == null) {
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
        this.status.validate();
    }

    public void startRecruiting() {
        this.status.startRecruiting();
    }

    public Long id() {
        return id;
    }

    public Long courseId() {
        return courseId;
    }

    public String type() {
        return type.name();
    }

    public String sessionStatus() {
        return status.sessionStatus();
    }

    public String recruitmentStatus() {
        return status.recruitmentStatus();
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
                ", courseId=" + courseId +
                ", type=" + type +
                ", coverImages=" + coverImages +
                ", period=" + period +
                ", status=" + status +
                ", students=" + students +
                ", paidCondition=" + paidCondition +
                '}';
    }
}
