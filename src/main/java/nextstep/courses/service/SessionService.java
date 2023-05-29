package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionUser;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    public SessionService(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Session save(Session session, Long courseId) {
        return sessionRepository.save(session, courseId);
    }

    @Transactional(readOnly = true)
    public Session findById(Long sessionId) {
        return sessionRepository.findById(sessionId);
    }

    @Transactional(readOnly = true)
    public List<Session> findByCourseId(Long courseId) {
        return sessionRepository.findByCourseId(courseId);
    }

    @Transactional
    public long enroll(Session session, SessionUser sessionUser) {
        session.enroll(sessionUser);
        return sessionRepository.saveSessionUser(session, sessionUser);
    }

    @Transactional(readOnly = true)
    public List<SessionUser> findAllUserBySessionId(Long sessionId) {
        return sessionRepository.findAllUserBySessionId(sessionId);
    }
}
