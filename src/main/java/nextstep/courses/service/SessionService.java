package nextstep.courses.service;

import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.EnrollmentCandidate;
import nextstep.courses.domain.enrollment.EnrollmentHistory;
import nextstep.courses.domain.enrollment.EnrollmentHistoryRepository;
import nextstep.courses.domain.enrollment.EnrollmentRepository;
import nextstep.courses.domain.enrollment.EnrollmentStatus;
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
    private final EnrollmentHistoryRepository historyRepository;

    public SessionService(SessionRepository sessionRepository, EnrollmentRepository enrollmentRepository, EnrollmentHistoryRepository historyRepository) {
        this.sessionRepository = sessionRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.historyRepository = historyRepository;
    }

    public void enroll(Long sessionId, Long nsUserId, Payment payment) {
        Session session = sessionRepository.findById(sessionId);
        List<EnrollmentCandidate> students = enrollmentRepository.findBySessionId(sessionId);
        Enrollment enrollment = session.createEnrollment(students);
        EnrollmentCandidate enroll = enrollment.enroll(nsUserId, payment);

        enrollmentRepository.save(enroll);
    }

    public void applyForEnrollment(Long sessionId, Long nsUserId, Payment payment) {
        Session session = sessionRepository.findById(sessionId);
        List<EnrollmentCandidate> students = enrollmentRepository.findBySessionId(sessionId);

        Enrollment enrollment = session.createEnrollment(students);
        EnrollmentCandidate candidate = enrollment.apply(nsUserId, payment);

        enrollmentRepository.save(candidate);

        EnrollmentHistory history = new EnrollmentHistory(sessionId, nsUserId);
        historyRepository.save(history);
    }

    public void approveEnrollment(Long sessionId, Long nsUserId, Long instructorId) {
        Session session = sessionRepository.findById(sessionId);
        List<EnrollmentCandidate> students = enrollmentRepository.findBySessionId(sessionId);

        Enrollment enrollment = session.createEnrollment(students);
        EnrollmentCandidate candidate = new EnrollmentCandidate(sessionId, nsUserId);
        enrollment.approve(candidate, instructorId);

        enrollmentRepository.update(candidate);

        EnrollmentHistory history = new EnrollmentHistory(sessionId, nsUserId, EnrollmentStatus.APPROVED, instructorId);
        historyRepository.save(history);
    }

}
