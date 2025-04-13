package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.infrastructure.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;
import nextstep.users.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final PaymentService paymentService;
    private final UserService userService;

    public SessionService(SessionRepository sessionRepository, PaymentService paymentService, UserService userService) {
        this.sessionRepository = sessionRepository;
        this.paymentService = paymentService;
        this.userService = userService;
    }

    @Transactional
    public void enroll(Long sessionId, String userId, String paymentId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의입니다."));

        NsUser user = userService.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Payment payment = null;
        if (session.isPaid()) {
            payment = paymentService.payment(paymentId);
        }

        session.enroll(user, payment);
        sessionRepository.update(session);
    }
} 