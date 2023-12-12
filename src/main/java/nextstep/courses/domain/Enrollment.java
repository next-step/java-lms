package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.payments.domain.Payment;

import java.util.List;

public class Enrollment {
    private Session session;
    private Long userNumber;

    public Enrollment(Session session, List<NsUserSession> nsUserSessions) {
        this.session = session;
        this.userNumber = (long) nsUserSessions.size();
    }

    public NsUserSession enroll(Payment payment) throws CannotEnrollException {
        session.checkStatus();
        session.checkPaidSession(payment, userNumber);
        return new NsUserSession(payment.getSessionId(), payment.getNsUserId());
    }
}
