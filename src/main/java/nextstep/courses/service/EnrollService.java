package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.Student;
import nextstep.courses.infrastructure.JdbcSessionRepository;
import nextstep.courses.infrastructure.JdbcStudentRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollService {
    private final PaymentService paymentService;
    private final JdbcSessionRepository sessionRepository;
    private final JdbcStudentRepository studentRepository;

    public EnrollService(PaymentService paymentService, JdbcSessionRepository sessionRepository, JdbcStudentRepository studentRepository) {
        this.paymentService = paymentService;
        this.sessionRepository = sessionRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Student enroll(Long sessionId, NsUser currentUser) {
        Session session = sessionRepository.findById(sessionId);
        paymentService.pay(session, currentUser);
        Student student = session.enroll(currentUser);
        studentRepository.save(student);
        return student;
    }
}
