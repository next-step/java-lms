package nextstep.courses.domain;

import nextstep.courses.domain.code.Selection;
import nextstep.courses.domain.code.SessionStatus;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
                   List<Thumbnail> thumbnails,
                   LocalDate startDate,
                   LocalDate endDate,
                   String sessionStatus,
                   Enrollment enrollment,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
        this(id, courseId, title, new Period(startDate, endDate), thumbnails, SessionStatus.valueOf(sessionStatus),
                enrollment, createdAt, updatedAt);
    }

    public Session(long id,
                   long courseId,
                   String title,
                   Period period,
                   List<Thumbnail> thumbnails,
                   SessionStatus status,
                   Enrollment enrollment,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
        this(id, courseId, new SessionInformation(title, period, thumbnails, status), enrollment, createdAt, updatedAt);
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
        Student student = new Student(this.id, nsUser.getId(), Selection.WAITING);
        enrollment.enroll(payment.amount(), student, students);

        return student;
    }

}
