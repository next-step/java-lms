package nextstep.courses.service;

import nextstep.courses.domain.PaymentRepository;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.Enrollment;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentService {

    private final PaymentRepository paymentRepository;
    private final SessionRepository sessionRepository;

    public EnrollmentService(PaymentRepository paymentRepository, SessionRepository sessionRepository) {
        this.paymentRepository = paymentRepository;
        this.sessionRepository = sessionRepository;
    }

    @Transactional
    public Enrollment enroll(NsUser nsUser, Long sessionId){
        Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new NotFoundException(String.format("%d session을 찾을 수 없습니다.", sessionId)));
        Payment payment = paymentRepository.findByUser(nsUser).orElseThrow(() -> new NotFoundException(String.format("%s 유저의 payment를 찾을 수 없습니다.", nsUser.getUserId())));
        return nsUser.enrollSession(session, payment);
    }
}
