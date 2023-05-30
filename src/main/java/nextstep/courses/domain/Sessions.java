package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Sessions {
    private List<Session> sessions;

    public Sessions() {
        sessions = new ArrayList<>();
    }

    public void add(Session session) {
        sessions.add(session);
    }

    public boolean hasSession(Session session) {
        return sessions.contains(session);
    }
}
