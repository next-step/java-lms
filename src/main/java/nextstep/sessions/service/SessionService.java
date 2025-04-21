package nextstep.sessions.service;

import nextstep.payments.domain.Payments;
import nextstep.payments.exception.InvalidPaymentException;
import nextstep.payments.service.PaymentService;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.exception.SessionNotExists;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final PaymentService paymentService;

    public SessionService(SessionRepository sessionRepository, PaymentService paymentService) {
        this.sessionRepository = sessionRepository;
        this.paymentService = paymentService;
    }

    public void addAttendee(Long sessionId, NsUser nsUser) {
        Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new SessionNotExists("Session not found"));

        Payments payments = paymentService.sessionPayments(sessionId);

        if (payments.paidIncorrectly(nsUser.getId(), session.getPrice())) {
            throw new InvalidPaymentException("Invalid payment");
        }

        session.addAttendee(nsUser);
    }
}
