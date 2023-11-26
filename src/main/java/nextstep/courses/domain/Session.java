package nextstep.courses.domain;

import nextstep.courses.domain.code.SessionStatus;
import nextstep.courses.domain.code.SessionType;
import nextstep.courses.domain.strategy.EnrollFactory;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Session {

    private final long id;

    private final long courseId;
    private final Period period;
    private final Thumbnail thumbnail;
    private Enrollment enrollment;
    private final SessionStatus status;

    public Session(long id,
                   long courseId,
                   Period period,
                   Thumbnail thumbnail,
                   SessionType sessionType,
                   int capacity,
                   Amount amount,
                   SessionStatus status) {
        this.id = id;
        this.courseId = courseId;
        this.enrollment = EnrollFactory.create(sessionType, capacity, amount);
        this.period = period;
        this.thumbnail = thumbnail;
        this.status = status;
    }

    public Session(long id,
                   long courseId,
                   Period period,
                   Thumbnail thumbnail,
                   Enrollment enrollment,
                   SessionStatus status) {
        this.id = id;
        this.courseId = courseId;
        this.enrollment = enrollment;
        this.period = period;
        this.thumbnail = thumbnail;
        this.status = status;
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
