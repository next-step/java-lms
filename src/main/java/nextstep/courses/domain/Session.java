package nextstep.courses.domain;

import nextstep.courses.domain.code.SessionStatus;
import nextstep.courses.domain.code.SessionType;
import nextstep.courses.domain.strategy.EnrollFactory;
import nextstep.courses.domain.strategy.EnrollmentStrategy;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Session {

    private final long id;
    private final long courseId;
    private final Period period;
    private final Thumbnail thumbnail;
    private EnrollmentStrategy enrollment;
    private final SessionStatus status;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    public Session(long id,
                   long courseId,
                   Thumbnail thumbnail,
                   LocalDate startDate,
                   LocalDate endDate,
                   String sessionType,
                   String sessionStatus,
                   int capacity,
                   long amount,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
        this(id, courseId, new Period(startDate, endDate), thumbnail, SessionType.valueOf(sessionType), capacity,
                new Amount(amount), SessionStatus.valueOf(sessionStatus));
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Session(long id,
                   long courseId,
                   Period period,
                   Thumbnail thumbnail,
                   SessionType sessionType,
                   int capacity,
                   Amount amount,
                   SessionStatus status) {
        this(id, courseId, period, thumbnail, EnrollFactory.create(sessionType, capacity, amount), status,
                LocalDateTime.now(), null);
    }

    public Session(long id,
                   long courseId,
                   Period period,
                   Thumbnail thumbnail,
                   EnrollmentStrategy enrollment,
                   SessionStatus status,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
        this.id = id;
        this.courseId = courseId;
        this.enrollment = enrollment;
        this.period = period;
        this.thumbnail = thumbnail;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Student enroll(Payment payment,
                          NsUser nsUser,
                          Students students) {
        status.validateApply();

        Student student = new Student(this.id, nsUser.getId());
        enrollment.enroll(payment.amount(), student, students);

        return student;
    }

}
