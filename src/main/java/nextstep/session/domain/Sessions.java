package nextstep.session.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sessions {

    private List<Session> sessions = new ArrayList<>();

    public Sessions() {

    }

    public List<Session> getSessions() {
        return Collections.unmodifiableList(sessions);
    }

    public void add(final Session session) {
        sessions.add(session);
    }
}
