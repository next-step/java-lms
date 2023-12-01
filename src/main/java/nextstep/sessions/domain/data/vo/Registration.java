package nextstep.sessions.domain.data.vo;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.Session;
import nextstep.users.domain.NsUser;

public class Registration {

    private final Session session;
    private final NsUser user;
    private final Payment payment;

    public Registration(Session session, NsUser user, Payment payment) {
        this.session = session;
        this.user = user;
        this.payment = payment;
    }
}
