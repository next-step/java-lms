package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class JoinUser {

    private final NsUser user;
    private final Payment payment;

    public JoinUser(NsUser user, Payment payment) {
        this.user = user;
        this.payment = payment;
    }
}
