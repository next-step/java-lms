package nextstep.courses.domain;

import java.util.HashSet;
import java.util.Set;

public class SessionRegister {

    private final Set<Session> sessions = new HashSet<>();
    private SessionRepository sessionRepository;

    public SessionRegister(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void registerSession(Session session) {

        if (session.isRecruiting()) {
            this.sessions.add(session);
        }
    }

    int count() {
        return sessions.size();
    }
}
