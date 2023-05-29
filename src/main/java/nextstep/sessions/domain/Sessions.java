package nextstep.sessions.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sessions {
    private List<Session> sessions = new ArrayList<>();

    private Sessions() {
    }

    public static Sessions create() {
        return new Sessions();
    }

    public void add(Session session) {
        sessions.add(session);
    }

    public List<Session> getSessions() {
        return Collections.unmodifiableList(sessions);
    }

}
