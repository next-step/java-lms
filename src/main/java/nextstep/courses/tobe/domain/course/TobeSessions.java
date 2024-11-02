package nextstep.courses.tobe.domain.course;

import nextstep.courses.domain.Session;
import nextstep.courses.tobe.domain.TobeSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TobeSessions {

    private final List<TobeSession> sessions;

    private TobeSessions(List<TobeSession> sessions) {
        this.sessions = new ArrayList<>(sessions);
    }

    public TobeSessions(TobeSession... sessions) {
        this(List.of(sessions));
    }

    public void add(TobeSession session) {
        this.sessions.add(session);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TobeSessions sessions1 = (TobeSessions) o;
        return Objects.equals(sessions, sessions1.sessions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessions);
    }
}
