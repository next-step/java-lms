package nextstep.courses.domain.attendee;

import java.util.Objects;

public class Attendee {

    private final Long nsUserId;

    private final Long sessionId;

    public Attendee(Long nsUserId, Long sessionId) {
        this.nsUserId = nsUserId;
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendee attendee = (Attendee) o;
        return Objects.equals(nsUserId, attendee.nsUserId)
                && Objects.equals(sessionId, attendee.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nsUserId, sessionId);
    }
}
