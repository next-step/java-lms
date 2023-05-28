package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Sessions {
    private final List<Session> sessions = new ArrayList<>();

    public boolean isEmpty() {
        return sessions.isEmpty();
    }

    public void add(Session session) {
        sessions.add(session);
    }
}
