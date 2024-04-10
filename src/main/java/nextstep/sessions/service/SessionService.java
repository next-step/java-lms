package nextstep.sessions.service;

import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import nextstep.sessions.controller.SessionEnrollRequestDto;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private final PaymentService paymentService;
    private final SessionRepository sessionRepository;

    public SessionService(PaymentService paymentService, SessionRepository sessionRepository) {
        this.paymentService = paymentService;
        this.sessionRepository = sessionRepository;
    }

    public void enroll(SessionEnrollRequestDto request, NsUser requestUser) {
        final Session session = sessionRepository.findById(request.sessionId());
        session.assertCanEnroll(requestUser, request.requestDateTime());

        Payment payment = paymentService.pay(session, requestUser);

        session.enroll(requestUser, payment);
    }

}
