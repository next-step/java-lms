package nextstep.courses.domain;

import nextstep.courses.domain.code.SessionStatus;
import nextstep.courses.domain.code.SessionType;
import nextstep.courses.domain.strategy.FreeEnrollmentStrategy;
import nextstep.courses.domain.strategy.PaidEnrollmentStrategy;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Session {

    private final long sessionId;
    private final Period period;
    private final Thumbnail thumbnail;
    private Enrollment enrollment;
    private final SessionStatus status;

    public Session(long sessionId,
                   Period period,
                   Thumbnail thumbnail,
                   SessionType sessionType,
                   int capacity,
                   Amount amount,
                   SessionStatus status) {
        this.sessionId = sessionId;
        if (sessionType == SessionType.FREE) {
            this.enrollment = new FreeEnrollmentStrategy();
        }
        if (sessionType == SessionType.PAID) {
            this.enrollment = new PaidEnrollmentStrategy(capacity, amount);
        }
        this.period = period;
        this.thumbnail = thumbnail;
        this.status = status;
    }

    public Session(long sessionId,
                   Period period,
                   Thumbnail thumbnail,
                   Enrollment enrollment,
                   SessionStatus status) {
        this.sessionId = sessionId;
        this.enrollment = enrollment;
        this.period = period;
        this.thumbnail = thumbnail;
        this.status = status;
    }

    public Student enroll(Payment payment,
                          NsUser nsUser,
                          Students students) {
        status.validateApply();

        Student student = new Student(this.sessionId, nsUser.getId());
        enrollment.enroll(payment.amount(), student, students);

        return student;
    }

}
