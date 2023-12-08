package nextstep.sessions.domain.data.vo;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.Session;
import nextstep.users.domain.NsUser;

public class RegistrationInfo {

    private final Session session;
    private final UserPaymentInfo userPaymentInfo;

    public RegistrationInfo(Session session, UserPaymentInfo userPaymentInfo) {
        this.session = session;
        this.userPaymentInfo = userPaymentInfo;
    }

    public UserPaymentInfo userPaymentInfo() {
        return userPaymentInfo;
    }

    public long userId() {
        return userPaymentInfo.userId();
    }

    public long paymentId() {
        return userPaymentInfo.paymentId();
    }

    public boolean hasEqualUser(NsUser user) {
        return userPaymentInfo.hasEqualUser(user);
    }

    public Payment payment() {
        return userPaymentInfo.payment();
    }

    public NsUser user() {
        return userPaymentInfo.user();
    }
}
