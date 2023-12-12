package nextstep.courses.service;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.NsUserSession;
import nextstep.courses.domain.Session;
import nextstep.payments.domain.Payment;

import java.util.ArrayList;
import java.util.List;

public class SessionService {
    public void enroll(Payment payment) throws CannotEnrollException {
        // TODO : Session 정보 가져옴
        // Session session = sessionRepository.getById(payment.getSessionId());
        // List<NsUserSession> nsUserSessions = sessionRepository.getBySessionId(payment.getSessionId());
        Session session = null;
        List<NsUserSession> nsUserSessions = new ArrayList<>();
//        NsUserSession nsUserSession = session.enroll(payment);
        Enrollment enrollment = new Enrollment(session, nsUserSessions);
        NsUserSession nsUserSession = enrollment.enroll(payment);
        // TODO : 수강 내역 저장
        // sessionRepository.save(nsUserSession);
    }
}
