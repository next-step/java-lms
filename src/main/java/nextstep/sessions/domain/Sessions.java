package nextstep.sessions.domain;

import java.util.ArrayList;
import java.util.List;

public class Sessions {
    private List<Session> sessions;

    public Sessions() {
        this.sessions = new ArrayList<>();
    }

    public Sessions(Session session) {
        this.sessions.add(session);
    }
}
