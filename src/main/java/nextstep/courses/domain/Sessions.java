package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sessions {

    private final List<Session> sessions;

    public Sessions() {
        this.sessions = new ArrayList<>();
    }

    public List<Session> getSessions() {
        return Collections.unmodifiableList(this.sessions);
    }

    public void add(Session session) {
        this.sessions.add(session);
    }
}
