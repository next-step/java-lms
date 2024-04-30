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
    public void enrollSession(Long sessionId, NsUser nsUser) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("세션 정보가 없습니다."));

        Payment payment = paymentRepository.findByNsUser(nsUser)
                .orElseThrow(() -> new IllegalArgumentException("결제 정보가 없습니다."));

        List<Student> enrolledStudents = studentRepository.findBySessionId(sessionId);
        Student student = session.enroll(nsUser, enrolledStudents, payment);
        studentRepository.save(student);
    }

    @Transactional
    public void approveStudent(Long sessionId, NsUser user, Long studentId) {
        validateUserAuthority(sessionId, user);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("학생 정보가 없습니다."));

        student.approve();
        studentRepository.save(student);
    }

    private void validateUserAuthority(Long sessionId, NsUser user) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("세션 정보가 없습니다."));

        if (session.isOutOfControl(user)) {
            throw new IllegalArgumentException("해당 유저는 권한이 없습니다.");
        }
    }

    @Transactional
    public void disApproveStudent(Long sessionId, NsUser user, Long studentId) {
        validateUserAuthority(sessionId, user);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("학생 정보가 없습니다."));

        student.disApprove();
        studentRepository.save(student);
    }

}
