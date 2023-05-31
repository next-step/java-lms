package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Sessions {
    private List<Session> sessions;

    public Sessions() {
        this.sessions = new ArrayList<>();
    }

    public void add(Session session) {
        sessions.add(session);
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public int size() {
        return sessions.size();
    }

}
