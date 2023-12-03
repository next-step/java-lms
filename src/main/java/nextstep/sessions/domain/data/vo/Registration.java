package nextstep.sessions.domain.data.vo;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.Session;
import nextstep.users.domain.NsUser;

public class Registration {

    private final Session session;
    private final UserPaymentInfo userPaymentInfo;

    public Registration(Session session, NsUser user, Payment payment) {
        this.session = session;
        this.userPaymentInfo = new UserPaymentInfo(user, payment);
    }

    public boolean hasUser(NsUser user) {
        return userPaymentInfo.hasEqualUser(user);
    }
}
