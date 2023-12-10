package nextstep.courses.domain;

import java.util.Collections;
import java.util.List;

public class Sessions {

    private final List<Session> sessions;

    private Sessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public static Sessions of(List<Session> sessions) {
        return new Sessions(sessions);
    }

    public List<Session> getSessions() {
        return Collections.unmodifiableList(this.sessions);
    }

    public void add(Session session) {
        this.sessions.add(session);
    }
}
