package nextstep.courses.domain;

import java.util.Set;

public class Sessions {
    private final Set<Session> sessions;

    public Sessions(Session... sessions) {
        this.sessions = Set.of(sessions);
    }

    public boolean hasSession(Session session) {
        return sessions.contains(session);
    }
}
