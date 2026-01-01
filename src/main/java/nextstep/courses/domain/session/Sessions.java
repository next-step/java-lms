package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.List;

public class Sessions {
    private final List<Session> sessions;

    public Sessions() {
        this(new ArrayList<>());
    }

    public Sessions(List<Session> sessions) {
        this.sessions = new ArrayList<>(sessions);
    }

    public static Sessions empty() {
        return new Sessions(List.of());
    }

    public void add(Session session) {
        this.sessions.add(session);
    }
}
