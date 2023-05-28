package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionNextStepUserRepository;
import nextstep.courses.domain.SessionRepository;
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
    private final SessionNextStepUserRepository sessionNextStepUserRepository;

    public SessionService(UserRepository userRepository, SessionRepository sessionRepository, SessionNextStepUserRepository sessionNextStepUserRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.sessionNextStepUserRepository = sessionNextStepUserRepository;
    }

    public Session findSessionById(long sessionId) {
        Session session = sessionRepository.findById(sessionId);

        if (Objects.isNull(session)) {
            throw new SessionNotFoundException("존재하지 않는 강의입니다.");
        }

        session.updateUsers(sessionNextStepUserRepository.findUsersBySessionId(sessionId));
        return session;
    }

    @Transactional
    public void enrollUserToSession(long sessionId, long userId) {
        Session session = findSessionById(sessionId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 유저입니다."));

        sessionNextStepUserRepository.save(session.getId(), user.getId());
        session.enroll(user);
    }

}
