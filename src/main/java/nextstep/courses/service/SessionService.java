package nextstep.courses.service;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.NsUserSession;
import nextstep.courses.domain.Session;
import nextstep.payments.domain.Payment;

public class SessionService {
    public void enroll(Payment payment) throws CannotEnrollException {
        // TODO: Session 정보 가져옴
        // Session session = sessionRepository.getById(payment.getSessionId());
        Session session = null;
        NsUserSession nsUserSession = session.enroll(payment);
        // TODO : 수강 내역 저장
        // sessionRepository.save(nsUserSession);
    }
}
