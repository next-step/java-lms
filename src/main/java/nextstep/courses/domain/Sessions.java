package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Sessions {

    private List<Session> sessions = new ArrayList<>();

    public Sessions() {
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public void addSession(List<Session> sessions) {
        this.sessions.addAll(sessions);
    }

    public int size() {
        return sessions.size();
    }


}
