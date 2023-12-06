package nextstep.courses.domain.attendee;

import java.util.Objects;

public class Attendee {

    private final Long studentId;

    private final Long sessionId;

    public Attendee(Long studentId, Long sessionId) {
        this.studentId = studentId;
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendee attendee = (Attendee) o;
        return Objects.equals(studentId, attendee.studentId) && Objects.equals(sessionId, attendee.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, sessionId);
    }
}
