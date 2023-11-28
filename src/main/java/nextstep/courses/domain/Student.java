package nextstep.courses.domain;

import java.util.Objects;

public class Student {
    private final long nsUserId;
    private final long sessionId;
    private final boolean approved;

    public Student() {
        this(0L, 0L, true);
    }

    public Student(long nsUserId,
                   long sessionId,
                   boolean approved) {
        this.nsUserId = nsUserId;
        this.sessionId = sessionId;
        this.approved = approved;
    }

    public long getNsUserId() {
        return nsUserId;
    }

    public long getSessionId() {
        return sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(nsUserId, student.nsUserId) && Objects.equals(sessionId, student.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nsUserId, sessionId);
    }
}
