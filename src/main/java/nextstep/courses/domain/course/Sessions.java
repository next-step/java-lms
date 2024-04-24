package nextstep.courses.domain.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import nextstep.courses.domain.session.Session;

public class Sessions {

    private final List<Session> sessions;

    public Sessions() {
        this.sessions = new ArrayList<>();
    }

    public Sessions(final List<Session> sessions) {
        this.sessions = sessions;
    }

    public void add(final Session session) {
        validateNotExistedSession(session);

        this.sessions.add(session);
    }

    private void validateNotExistedSession(final Session session) {
        if (this.sessions.contains(session)) {
            throw new IllegalArgumentException("해당 과정에 이미 포함된 강의입니다. 강의: " + session);
        }
    }

    public boolean contains(final Session session) {
        return this.sessions.contains(session);
    }

    @Override
    public boolean equals(final Object otherSessions) {
        if (this == otherSessions) {
            return true;
        }

        if (otherSessions == null || getClass() != otherSessions.getClass()) {
            return false;
        }

        return Objects.equals(this.sessions, ((Sessions)otherSessions).sessions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.sessions);
    }
}
