package nextstep.courses.domain.session;

import java.util.Objects;

public class SelectionSession {
    private Long id;
    private Long sessionId;
    private Long selectionSessionId;

    public SelectionSession(Long sessionId, Long selectionSessionId) {
        this(0L, sessionId, selectionSessionId);
    }

    public SelectionSession(Long id, Long sessionId, Long selectionSessionId) {
        this.id = id;
        this.sessionId = sessionId;
        this.selectionSessionId = selectionSessionId;
    }

    public Long getId() {
        return id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getSelectionSessionId() {
        return selectionSessionId;
    }

    @Override
    public String toString() {
        return "SelectionSession{" +
                "id=" + id +
                ", sessionId=" + sessionId +
                ", selectionSessionId=" + selectionSessionId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectionSession that = (SelectionSession) o;
        return Objects.equals(sessionId, that.sessionId) && Objects.equals(selectionSessionId, that.selectionSessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, selectionSessionId);
    }
}
