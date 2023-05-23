package nextstep.courses.domain;

import java.util.HashSet;
import java.util.Set;

public class SessionRegister {

    private final Set<Session> sessions = new HashSet<>();

    public SessionRegister() {
    }

    public void registerSession(Session session) {
        if (session.isRecruiting()) {
            sessions.add(session);
        }
    }

    int count() {
        return sessions.size();
    }
}
