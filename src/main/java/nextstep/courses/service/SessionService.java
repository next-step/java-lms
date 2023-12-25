package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;

public class SessionService {
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    public SessionService(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    public void enroll(long sessionId, String userId) {
        NsUser loginUser = this.userRepository.findByUserId(userId);
        Session session = this.sessionRepository.findById(sessionId);

        session.enroll(loginUser);
        sessionRepository.save(session);
    }
}
