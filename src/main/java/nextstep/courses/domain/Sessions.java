package nextstep.courses.domain;

import java.util.HashSet;
import java.util.Set;

public class Sessions {

    private final Set<Session> sessions;

    public Sessions(Set<Session> sessions) {
        this.sessions = sessions;
    }

    public Sessions() {
        this.sessions = new HashSet<>();
    }

    public void addSession(Session session) {
        String errorMessage = "이미 등록된 강의입니다.";

        if (sessions.contains(session)) {
            throw new IllegalArgumentException(errorMessage);
        }

        sessions.add(session);
    }

    public Set<Session> getSessions() {
        return new HashSet<>(sessions);
    }
}
