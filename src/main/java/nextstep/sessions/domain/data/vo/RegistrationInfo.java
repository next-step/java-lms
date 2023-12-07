package nextstep.sessions.domain.data.vo;

import nextstep.sessions.domain.data.Session;

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
}
