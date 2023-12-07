package nextstep.courses.service;

import nextstep.courses.domain.session.PayType;
import nextstep.payments.domain.Payment;

public interface SessionService {

    boolean supports(PayType payType);
    void enroll(Payment payment);
}
