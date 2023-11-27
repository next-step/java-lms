package nextstep.courses.domain;

import nextstep.courses.domain.code.SessionStatus;
import nextstep.courses.domain.strategy.EnrollmentStrategy;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Session {

    private final long id;
    private final long courseId;
    private final String title;
    private final Period period;
    private final Thumbnail thumbnail;
    private EnrollmentStrategy enrollment;
    private final SessionStatus status;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    public Session(long id,
                   long courseId,
                   String title,
                   Thumbnail thumbnail,
                   LocalDate startDate,
                   LocalDate endDate,
                   EnrollmentStrategy enrollment,
                   String sessionStatus,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
        this(id, courseId, title, new Period(startDate, endDate), thumbnail, enrollment, SessionStatus.valueOf(sessionStatus), createdAt, updatedAt);
    }

    public Session(long id,
                   long courseId,
                   String title,
                   Period period,
                   Thumbnail thumbnail,
                   EnrollmentStrategy enrollment,
                   SessionStatus status,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
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
