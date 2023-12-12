package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sessions {
    private final List<Session> sessions;

    public static Sessions initialize() {
        return new Sessions(Collections.emptyList());
    }

    public void addSession(Session session) {
        sessions.add(session);
        new Sessions(sessions);
    }

    private Sessions(List<Session> sessions) {
        this.sessions = new ArrayList<>(sessions);
    }

    public List<Session> getSessions() {
        return Collections.unmodifiableList(sessions);
    }

    public int size() {
        return sessions.size();
    }
}
