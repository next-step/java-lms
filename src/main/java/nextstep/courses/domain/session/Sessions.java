package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sessions {

    private final List<Session> sessions;

    public Sessions() {
        this.sessions = new ArrayList<>();
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
