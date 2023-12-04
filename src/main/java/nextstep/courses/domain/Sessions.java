package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sessions {
    private final List<Session> sessions;

    public Sessions() {
        this.sessions = new ArrayList<>();
    }

    public List<Session> findSessions() {
        return Collections.unmodifiableList(sessions);
    }

    public void addSession(Session session) {
        sessions.add(session);
    }
}
