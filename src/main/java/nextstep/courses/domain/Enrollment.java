package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;

public class Enrollment {
    private Session session;
    private NsUserSessions nsUserSessions;

    public Enrollment(Session session, NsUserSessions nsUserSessions) {
        this.session = session;
        this.nsUserSessions = nsUserSessions;
    }

    public NsUserSession enroll(Payment payment) throws CannotEnrollException {
        session.enroll(payment, nsUserSessions.userNumber());
        return new NsUserSession(payment.getSessionId(), payment.getNsUserId());
    }
}
