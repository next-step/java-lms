package nextstep.session.domain;

import nextstep.users.domain.NsUser;

public class SessionPayment {

    private NsUser participant;

    private PaymentType paymentType;

    public SessionPayment(NsUser user, PaymentType paymentType) {
        this.participant = user;
        this.paymentType = paymentType;
    }
}
