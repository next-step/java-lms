package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.exception.SessionEnrollmentException;
import nextstep.courses.exception.SessionNotFoundException;
import nextstep.users.domain.User;
import nextstep.users.domain.UserRepository;
import nextstep.users.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class SessionService {
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    public SessionService(UserRepository userRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    public Session findSessionById(long sessionId) {
        Session session = sessionRepository.findBySessionId(sessionId);

        if (Objects.isNull(session)) {
            throw new SessionNotFoundException("존재하지 않는 강의입니다.");
        }

        return session;
    }

    @Transactional
    public void enrollUserToSession(long sessionId, long userId) {
        Session session = findSessionById(sessionId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 유저입니다."));

        checkUserAlreadyEnrolled(sessionId, userId);

        session.enroll(user);
        sessionRepository.enrollUser(session);
    }

    private void checkUserAlreadyEnrolled(long sessionId, long userId) {
        boolean isAlreadyEnrolled = sessionRepository.findUsersBySessionId(sessionId)
                .stream()
                .anyMatch(user -> user.getId() == userId);

        if (isAlreadyEnrolled) {
            throw new SessionEnrollmentException("이미 등록된 유저입니다.");
        }
    }

}
