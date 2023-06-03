package nextstep.courses.domain.session;

import nextstep.courses.DuplicateSessionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sessions {
    private List<Session> sessions;

    public Sessions() {
        this(new ArrayList<>());
    }

    public Sessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public void addSession(Session session) {
        validateDuplicateSession(session);
        sessions.add(session);
    }

    private void validateDuplicateSession(Session session) {
        if (sessions.contains(session)) {
            throw new DuplicateSessionException("동일 강의 등록 불가합니다.");
        }
    }

    public boolean isContainsSameSession(Sessions otherSessions) {
        for(Session session: otherSessions.sessions) {
            if (sessions.contains(session)) {
                return true;
            }
        }
        return false;
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
