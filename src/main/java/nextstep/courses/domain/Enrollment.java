package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.payments.domain.Payment;

import java.util.List;

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
