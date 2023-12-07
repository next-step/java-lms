package nextstep.courses.domain;

import java.util.Objects;

public class NsUserSession {
    private Long sessionId;
    private Long nsUserId;

    public NsUserSession(Long sessionId, Long nsUserId) {
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NsUserSession that = (NsUserSession) o;
        return sessionId.equals(that.sessionId) && nsUserId.equals(that.nsUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, nsUserId);
    }
}
