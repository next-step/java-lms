package nextstep.sessions.domain;

import java.util.List;

public class Sessions {
    private final List<Session> sessions;

    public Sessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public void add(Session session) {
        checkDuplicateSession(session);
        this.sessions.add(session);
    }

    private void checkDuplicateSession(Session session) {
        if (this.sessions.contains(session)) {
            throw new IllegalArgumentException("이미 존재하는 강의는 추가할 수 없습니다.");
        }
    }
}
