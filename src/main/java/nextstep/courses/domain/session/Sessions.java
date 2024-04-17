package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.List;

public class Sessions {

    private final List<Session> sessions = new ArrayList<>();

    public Sessions() {}

    public void add(Session session) {
        this.sessions.add(session);
    }

    public boolean hasSession(Session session) {
        return sessions.contains(session);
    }
}
