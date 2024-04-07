package nextstep.users.domain;

/*
일종의 수강 영수증 같은 용도로 만들었습니다.
 */

import nextstep.courses.domain.Session;
import nextstep.payments.domain.Payment;

public class Enrollment {
    private final NsUser nsUser;
    private final Payment payment;
    private final Session session;

    public Enrollment(NsUser nsUser, Payment payment, Session session) {
        this.nsUser = nsUser;
        this.payment = payment;
        this.session = session;
    }
}
