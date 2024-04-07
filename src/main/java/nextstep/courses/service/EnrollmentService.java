package nextstep.courses.service;

import nextstep.courses.domain.PaymentRepository;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.Enrollment;
import nextstep.users.domain.NsUser;

public class EnrollmentService {

    private final PaymentRepository paymentRepository;
    private final SessionRepository sessionRepository;

    public EnrollmentService(PaymentRepository paymentRepository, SessionRepository sessionRepository) {
        this.paymentRepository = paymentRepository;
        this.sessionRepository = sessionRepository;
    }

    public Enrollment enroll(NsUser nsUser, Long sessionId){
        Session session = sessionRepository.findById(sessionId);
        Payment payment = paymentRepository.findByUser(nsUser);
        return nsUser.enrollSession(session, payment);
    }
}
