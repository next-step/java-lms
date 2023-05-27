package nextstep.courses.domain;

import java.util.HashSet;
import java.util.Set;

public class Sessions {
    private final Set<Session> sessions;

    public Sessions() {
        this(new HashSet<>());
    }

    public Sessions(Set<Session> sessions) {
        this.sessions = sessions;
    }

    public void add(Session session) {
        if(sessions.contains(session)) {
            throw new IllegalArgumentException("이미 등록된 강의입니다: " + session.title());
        }
        sessions.add(session);
    }
}
