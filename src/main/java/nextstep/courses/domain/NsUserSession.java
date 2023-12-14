package nextstep.courses.domain;

import java.util.Objects;

public class NsUserSession {
    private long sessionId;
    private long nsUserId;
    private boolean registered;

    public NsUserSession(long sessionId, long nsUserId) {
        this(sessionId, nsUserId, false);
    }

    public NsUserSession(Long sessionId, Long nsUserId, boolean registered) {
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.registered = registered;
    }
    public boolean matchSessionIdAndUserId(long sessionId, long nsUserId){
        return this.sessionId == sessionId && this.nsUserId == nsUserId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NsUserSession that = (NsUserSession) o;
        return sessionId == that.sessionId && nsUserId == that.nsUserId && registered == that.registered;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, nsUserId, registered);
    }

    public Long sessionId() {
        return sessionId;
    }

    public Long nsUserId() {
        return nsUserId;
    }

    public boolean registered() {
        return registered;
    }

    @Override
    public String toString() {
        return "NsUserSession{" +
                "sessionId=" + sessionId +
                ", nsUserId=" + nsUserId +
                ", registered=" + registered +
                '}';
    }
}
