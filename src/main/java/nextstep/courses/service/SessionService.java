package nextstep.courses.service;

import nextstep.courses.domain.session.EnrollmentRepository;
import nextstep.courses.domain.session.EnrollmentStatus;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
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
    public void enroll(NsUser loginUser, long sessionId, Payment payment) {
        Session session = findSessionById(sessionId);

        session.enroll(loginUser, payment);

        enrollmentRepository.save(sessionId, loginUser);
    }

    @Transactional
    public void approve(NsUser user, long sessionId) {
        Session session = findSessionById(sessionId);

        session.approve(user);

        enrollmentRepository.updateEnrollmentStatus(sessionId, user.getId(), EnrollmentStatus.APPROVED);
    }

    @Transactional
    public void reject(NsUser user, long sessionId) {
        Session session = findSessionById(sessionId);

        session.reject(user);

        enrollmentRepository.updateEnrollmentStatus(sessionId, user.getId(), EnrollmentStatus.REJECTED);
    }

    private Session findSessionById(long sessionId) {
        return sessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의입니다."));
    }
}
