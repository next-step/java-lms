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

    public Session getSession(long id) {
        return sessions.stream()
                .filter(session -> session.equals(new Session(id)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 세션을 찾을 수 없습니다."));
    }
}
