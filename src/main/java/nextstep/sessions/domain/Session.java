package nextstep.sessions.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Session {

    private final long id;

    private final String title;

    private final SessionState state;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Session(String title) {
        this(title, SessionState.PREPARING);
    }

    public Session(String title, SessionState state) {
        this(title, state, LocalDateTime.now());
    }

    public Session(String title, SessionState state, LocalDateTime createdAt) {
        this(0L, title, state, createdAt, null);
    }

    public Session(long id, String title, SessionState state, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.state = state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public boolean isPossibleRegistration() {
        return state.isRecruiting();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Session session = (Session) other;
        return id == session.id && Objects.equals(title, session.title) && state == session.state && Objects.equals(createdAt, session.createdAt) && Objects.equals(updatedAt, session.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, state, createdAt, updatedAt);
    }
}
