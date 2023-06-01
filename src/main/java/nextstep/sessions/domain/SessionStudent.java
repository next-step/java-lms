package nextstep.sessions.domain;

import java.util.Objects;

public class SessionStudent {
    private Long sessionId;
    private Long nsUserId;

    public SessionStudent(Long sessionId, Long nsUserId) {
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionStudent that = (SessionStudent) o;
        return Objects.equals(sessionId, that.sessionId) && Objects.equals(nsUserId, that.nsUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, nsUserId);
    }

    @Override
    public String toString() {
        return "SessionStudent{" +
                "sessionId=" + sessionId +
                ", nsUserId=" + nsUserId +
                '}';
    }
}
