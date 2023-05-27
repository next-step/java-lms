package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
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
    public long saveSessionUser(Session session, NsUser nextStepUser) {
        session.enroll(nextStepUser);
        return sessionRepository.saveSessionUser(session, nextStepUser);
    }

    @Transactional(readOnly = true)
    public List<NsUser> findAllUserBySessionId(Long sessionId) {
        List<String> nextStepUserIds = sessionRepository.findAllUserBySessionId(sessionId);
        return nextStepUserIds.stream()
                .map(userRepository::findByUserId)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
