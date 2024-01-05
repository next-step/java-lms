package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.session.SessionCondition;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.payments.domain.Payment;

public class Enrollment {
    private final SessionStatus sessionStatus;
    private final SessionCondition sessionCondition;
    private final boolean approvalRequired;

    public Enrollment(SessionStatus sessionStatus, SessionCondition sessionCondition, boolean approvalRequired) {
        this.sessionStatus = sessionStatus;
        this.sessionCondition = sessionCondition;
        this.approvalRequired = approvalRequired;
    }

    public Student enroll(Payment payment) throws CannotEnrollException {
        sessionStatus.canEnroll();
        sessionCondition.match(payment);
        return new Student(payment.getSessionId(), payment.getNsUserId(), EnrollmentStatus.get(approvalRequired));
    }
}
