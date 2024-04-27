package nextstep.sessions.service;

import nextstep.payments.domain.Payment;
import nextstep.payments.infrastructure.PaymentRepository;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.Student;
import nextstep.sessions.domain.StudentRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;

    public SessionService(
            SessionRepository sessionRepository,
            PaymentRepository paymentRepository,
            StudentRepository studentRepository
    ) {
        this.sessionRepository = sessionRepository;
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void registerSession(Long sessionId, NsUser nsUser) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("세션 정보가 없습니다."));

        Payment payment = paymentRepository.findByNsUser(nsUser)
                .orElseThrow(() -> new IllegalArgumentException("결제 정보가 없습니다."));

        //session.register(nsUser, payment);
        //sessionRepository.save(session);
        //studentRepository.save(nsUser, session);

        //todo : feedback
        List<Student> enrolledStudents = studentRepository.findBySessionId(sessionId);
        Student student = session.enroll(nsUser, enrolledStudents, payment);
        studentRepository.save(student);
    }

}
