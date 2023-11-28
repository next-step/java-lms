package nextstep.courses.domain;

import nextstep.courses.domain.code.Enrollment;
import nextstep.courses.domain.code.EnrollmentStatus;
import nextstep.courses.domain.code.SessionStatus;
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
    private final SessionStatus status;
    private final Enrollment enrollment;
    private final EnrollmentStatus enrollmentStatus;
    private final int capacity;
    private final Amount amount;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    public Session(long id,
                   long courseId,
                   String title,
                   Thumbnail thumbnail,
                   LocalDate startDate,
                   LocalDate endDate,
                   String sessionStatus,
                   String enrollment,
                   String enrollmentStatus,
                   int capacity,
                   long amount,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
        this(id, courseId, title, new Period(startDate, endDate), thumbnail, SessionStatus.valueOf(sessionStatus),
                Enrollment.valueOf(enrollment), EnrollmentStatus.valueOf(enrollmentStatus), capacity, new Amount(amount), createdAt, updatedAt);
    }

    public Session(long id,
                   long courseId,
                   String title,
                   Period period,
                   Thumbnail thumbnail,
                   SessionStatus status,
                   Enrollment enrollment,
                   EnrollmentStatus enrollmentStatus,
                   int capacity,
                   Amount amount,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.enrollmentStatus = enrollmentStatus;
        this.capacity = capacity;
        this.period = period;
        this.thumbnail = thumbnail;
        this.status = status;
        this.enrollment = enrollment;
        this.amount = amount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Student enroll(Payment payment,
                          NsUser nsUser,
                          Students students) {
        Student student = new Student(this.id, nsUser.getId());
        enrollment.enroll(enrollmentStatus, payment.amount(), amount, capacity, student, students);

        return student;
    }

}
