package nextstep.sessions.domain.data.vo;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.Session;

public class Registration {

    private final Session session;
    private final Payment payment;

    public Registration(Session session, Payment payment) {
        this.session = session;
        this.payment = payment;
    }
}
