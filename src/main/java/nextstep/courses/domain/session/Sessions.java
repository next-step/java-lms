package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.List;

public class Sessions {
    private final List<Session> sessions;

    public Sessions(List<Session> sessions) {
        this.sessions = new ArrayList<>(sessions);
    }

    public void add(Session session) {
        sessions.add(session);
    }

    public int size() {
        return sessions.size();
    }

    public Session findByCohort(int cohort) {
        return sessions.stream()
                .filter(session -> session.getCohort() == cohort)
                .findFirst()
                .orElse(null);
    }
}
