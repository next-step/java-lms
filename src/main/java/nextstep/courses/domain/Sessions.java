package nextstep.courses.domain;

import nextstep.courses.DuplicateSessionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sessions {
    private List<Session> sessions;

    public Sessions() {
        this.sessions = new ArrayList<>();
    }

    public void addSession(Session session) {
        validate(session);
        sessions.add(session);
    }

    private void validate(Session session) {
        for(Session s: sessions) {
            validateDuplicateSession(s, session);
        }
    }

    private void validateDuplicateSession(Session session1, Session session2) {
        if (session1.equals(session2)) {
            throw new DuplicateSessionException("동일 강의 등록 불가합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sessions sessions1 = (Sessions) o;
        return Objects.equals(sessions, sessions1.sessions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessions);
    }
}
