package nextstep.courses.service;

import nextstep.courses.domain.session.PayType;
import nextstep.courses.exception.SessionEnrollException;
import nextstep.payments.domain.Payment;

public interface SessionService {

    boolean supports(PayType payType);
    void enroll(Payment payment) throws SessionEnrollException;
}
