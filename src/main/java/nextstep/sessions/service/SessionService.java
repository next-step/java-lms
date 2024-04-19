package nextstep.sessions.service;

import nextstep.payments.domain.Payment;
import nextstep.payments.infrastructure.PaymentRepository;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final PaymentRepository paymentRepository;

    public SessionService(SessionRepository sessionRepository, PaymentRepository paymentRepository) {
        this.sessionRepository = sessionRepository;
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public void registerSession(Long sessionId, NsUser nsUser) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("세션 정보가 없습니다."));

        Payment payment = paymentRepository.findByNsUser(nsUser)
                .orElseThrow(() -> new IllegalArgumentException("결제 정보가 없습니다."));

        session.register(nsUser, payment);
        sessionRepository.save(session);
    }

}
