package nextstep.courses.service;

import nextstep.courses.domain.session.EnrollmentRepository;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    private final EnrollmentRepository enrollmentRepository;

    private final PaymentService paymentService;

    public SessionService(SessionRepository sessionRepository, EnrollmentRepository enrollmentRepository, PaymentService paymentService) {
        this.sessionRepository = sessionRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.paymentService = paymentService;
    }

    @Transactional
    public void enroll(NsUser loginUser, long sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의입니다."));

        session.enroll(loginUser, paymentService.payment(loginUser.getUserId()));
        enrollmentRepository.save(sessionId, loginUser);
    }
}
