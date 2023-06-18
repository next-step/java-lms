package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sessions {

    private final List<Session> sessions;

    public Sessions() {
        this(new ArrayList<>());
    }

    public Sessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public void add(Session session) {
        this.sessions.add(session);
    }

    public List<Session> getSession() {
        return Collections.unmodifiableList(sessions);
    }

    public int size() {
        return sessions.size();
    }
}
