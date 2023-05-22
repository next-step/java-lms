package nextstep.courses.domain;

import java.util.HashSet;
import java.util.Set;

public class Sessions {
    private final Set<Session> sessions = new HashSet<>();

    public void add(Session session) {
        if(sessions.contains(session)) {
            throw new IllegalArgumentException("이미 등록된 강의입니다: " + session.name());
        }
        sessions.add(session);
    }
}
