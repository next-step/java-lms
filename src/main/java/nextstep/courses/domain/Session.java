package nextstep.courses.domain;

import nextstep.courses.domain.code.EnrollmentStatus;
import nextstep.courses.domain.code.EnrollmentType;
import nextstep.courses.domain.code.SessionStatus;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Session {

    private final long id;
    private final long courseId;
    private final SessionInformation sessionInformation;
    private final Enrollment enrollment;
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
                EnrollmentType.valueOf(enrollment), EnrollmentStatus.valueOf(enrollmentStatus), capacity, new Amount(amount), createdAt, updatedAt);
    }

    public Session(long id,
                   long courseId,
                   String title,
                   Period period,
                   Thumbnail thumbnail,
                   SessionStatus status,
                   EnrollmentType enrollmentType,
                   EnrollmentStatus enrollmentStatus,
                   int capacity,
                   Amount amount,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
        this(id, courseId, new SessionInformation(title, period, thumbnail, status), new Enrollment(enrollmentType,
                enrollmentStatus, capacity,
                amount), createdAt, updatedAt);
    }

    public Session(long id,
                   long courseId,
                   SessionInformation sessionInformation,
                   Enrollment enrollment,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
        this.id = id;
        this.courseId = courseId;
        this.sessionInformation = sessionInformation;
        this.enrollment = enrollment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Student enroll(Payment payment,
                          NsUser nsUser,
                          Students students) {
        Student student = new Student(this.id, nsUser.getId());
        enrollment.enroll(payment.amount(), student, students);

        return student;
    }

}
