package nextstep.sessions.service;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.Enrollment;
import nextstep.sessions.domain.Session;
import nextstep.sessions.infrastructore.SessionRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void requestJoin(Session session, NsUser loginUser) {
        LocalDateTime now = LocalDateTime.now();
        Enrollment enrollment = session.requestJoin(loginUser, now);
        // 수강 신청 정보 저장

        if (session.isAutomaticSelection()) {
            Payment payment = session.toPayment(loginUser, now);
            // 저장 후 결제 요청
        }
    }

    public void joinSession(Session session, NsUser loginUser, Payment payment) {
        session.join(loginUser, payment);
    }
}
