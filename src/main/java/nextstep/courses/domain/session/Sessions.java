package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.List;

public class Sessions {
    private final List<Session> sessions;

    public Sessions() {
        this.sessions = new ArrayList<>();
    }

    public void add(Session session) {
        sessions.add(session);
    }

    public int size() {
        return sessions.size();
    }
}
