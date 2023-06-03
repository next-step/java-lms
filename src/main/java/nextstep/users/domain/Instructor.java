package nextstep.users.domain;

import java.util.Objects;

public class Instructor {
    private Long id;
    private Long nsUserId;
    private Long sessionId;

    public Instructor(Long nsUserId, Long sessionId) {
        this(0L, nsUserId, sessionId);
    }

    public Instructor(Long id, Long nsUserId, Long sessionId) {
        this.id = id;
        this.nsUserId = nsUserId;
        this.sessionId = sessionId;
    }

    public Long getId() {
        return id;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return Objects.equals(nsUserId, that.nsUserId) && Objects.equals(sessionId, that.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nsUserId, sessionId);
    }
}
