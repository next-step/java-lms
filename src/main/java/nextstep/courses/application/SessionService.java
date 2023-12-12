package nextstep.courses.application;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.NsUser;

public class SessionService {
    private SessionRepository sessionRepository;

    public void enroll(NsUser loginUser, Long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        session.enroll(loginUser);
    }
}
