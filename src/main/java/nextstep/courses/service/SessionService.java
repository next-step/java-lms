package nextstep.courses.service;

import nextstep.courses.domain.session.EnrolledStudent;
import nextstep.courses.domain.session.Enrollment;
import nextstep.courses.domain.session.EnrollmentCandidate;
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

    public void applyForEnrollment(Long sessionId, Long nsUserId, Payment payment) {
        Session session = sessionRepository.findById(sessionId);
        List<EnrolledStudent> students = enrollmentRepository.findBySessionId(sessionId);

        Enrollment enrollment = session.createEnrollment(students);
        EnrollmentCandidate candidate = enrollment.apply(nsUserId, payment);

        enrollmentRepository.saveCandidate(candidate);
    }

    public void approveEnrollment(Long sessionId, Long nsUserId, Long instructorId) {
        Session session = sessionRepository.findById(sessionId);
        List<EnrolledStudent> students = enrollmentRepository.findBySessionId(sessionId);

        Enrollment enrollment = session.createEnrollment(students);
        EnrollmentCandidate candidate = new EnrollmentCandidate(sessionId, nsUserId);
        EnrolledStudent student = enrollment.approve(candidate, instructorId);

        enrollmentRepository.updateCandidate(candidate);
        enrollmentRepository.save(student);
    }

}
