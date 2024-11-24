package nextstep.courses.service;

import nextstep.courses.domain.session.*;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    private final EnrollmentRepository enrollmentRepository;


    public SessionService(SessionRepository sessionRepository, EnrollmentRepository enrollmentRepository) {
        this.sessionRepository = sessionRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Transactional
    public void enroll(long nsUserId, long sessionId, Payment payment) {
        Session session = findSessionById(sessionId);
        Student student = Student.of(nsUserId, sessionId, EnrollmentStatus.PENDING);

        session.enroll(student, payment);

        enrollmentRepository.save(sessionId, student);
    }

    @Transactional
    public void approve(long nsUserId, long sessionId) {
        Session session = findSessionById(sessionId);
        Student student = Student.of(nsUserId, sessionId, EnrollmentStatus.PENDING);

        session.approve(student);

        enrollmentRepository.updateEnrollmentStatus(sessionId, student);
    }

    @Transactional
    public void reject(long nsUserId, long sessionId) {
        Session session = findSessionById(sessionId);
        Student student = Student.of(nsUserId, sessionId, EnrollmentStatus.PENDING);

        session.reject(student);

        enrollmentRepository.updateEnrollmentStatus(sessionId, student);
    }

    private Session findSessionById(long sessionId) {
        return sessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의입니다."));
    }
}
