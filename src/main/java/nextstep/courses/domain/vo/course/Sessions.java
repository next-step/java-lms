package nextstep.courses.domain.vo.course;

import nextstep.courses.domain.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sessions {

    private final List<Session> sessions;

    private Sessions(List<Session> sessions) {
        this.sessions = new ArrayList<>(sessions);
    }

    public Sessions(Session... sessions) {
        this(List.of(sessions));
    }

    public void add(Session session) {
        this.sessions.add(session);
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
