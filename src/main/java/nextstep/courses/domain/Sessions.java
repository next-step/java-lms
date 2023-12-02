package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sessions {
    private List<Session> sessions;

    public static Sessions initialize() {
        return new Sessions(Collections.emptyList());
    }

    public Sessions addSession(Session session) {
        ArrayList<Session> sessions = new ArrayList<>(this.sessions);
        sessions.add(session);
        return new Sessions(sessions);
    }

    private Sessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public List<Session> getSessions() {
        return Collections.unmodifiableList(sessions);
    }

    public int size() {
        return sessions.size();
    }
}
