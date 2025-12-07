package nextstep.courses.service;

import nextstep.courses.domain.session.EnrolledStudent;
import nextstep.courses.domain.session.Enrollment;
import nextstep.courses.domain.session.EnrollmentRepository;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.payments.domain.Payment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SessionService {
    private final SessionRepository sessionRepository;
    private final EnrollmentRepository enrollmentRepository;

    public SessionService(SessionRepository sessionRepository, EnrollmentRepository enrollmentRepository) {
        this.sessionRepository = sessionRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public void enroll(Long sessionId, Long nsUserId, Payment payment) {
        Session session = sessionRepository.findById(sessionId);
        List<EnrolledStudent> students = enrollmentRepository.findBySessionId(sessionId);
        Enrollment enrollment = session.createEnrollment(students);
        EnrolledStudent enroll = enrollment.enroll(nsUserId, payment);

        enrollmentRepository.save(enroll);
    }
}
