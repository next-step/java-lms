package nextstep.courses.domain;

import nextstep.utils.DomainId;

import java.util.Objects;

public class SessionId implements DomainId {
    private Long sessionId;

    public SessionId() {
    }

    public SessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public Long value() {
        return sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionId sessionId1 = (SessionId) o;
        return Objects.equals(sessionId, sessionId1.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId);
    }

    @Override
    public String toString() {
        return "SessionId{" +
                "sessionId=" + sessionId +
                '}';
    }
}
