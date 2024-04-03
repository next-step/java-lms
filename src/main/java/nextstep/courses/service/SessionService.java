package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SessionService {

    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Resource(name = "paymentService")
    private PaymentService paymentService;


    public Session createSession(Session session){
        return sessionRepository.save(session);
    }

    public void registerStudent(NsUser student, Long sessionId, String paymentId){
        Session session = sessionRepository.findById(sessionId);
        Payment payment = paymentService.payment(paymentId);

        session.addStudent(student, payment);
    }
}
