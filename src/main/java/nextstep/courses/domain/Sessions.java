package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Sessions {
    private final List<Session> sessions;

    public Sessions() {
        this.sessions = new ArrayList<>();
    }

    public Sessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public void add(Session session) {
        this.sessions.add(session);
    }

    public void addAll(Sessions sessions) {
        this.sessions.addAll(sessions.getSessions());
    }

    public int count() {
        return sessions.size();
    }

    public List<Session> getSessions() {
        return sessions;
    }
}
