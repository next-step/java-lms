package nextstep.courses.domain;

import nextstep.courses.domain.code.SessionStatus;
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
                   Enrollment enrollment,
                   Amount amount,
                   SessionStatus status) {
        this.sessionId = sessionId;
        this.enrollment = enrollment;
        this.period = period;
        this.thumbnail = thumbnail;
        this.status = status;
    }

    public Student enrol(Payment payment,
                         NsUser nsUser,
                         Students students) {
        status.validateApply();

        Student student = new Student(this.sessionId, nsUser.getId());
        enrollment.enroll(payment.amount(), student, students);

        return student;
    }

}
