package nextstep.sessions.domain;

import java.util.Objects;

public class Student {
    private final long nsUserId;
    private final long sessionId;

    public Student(long nsUserId, long sessionId) {
        this.nsUserId = nsUserId;
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Student student = (Student) object;
        return nsUserId == student.nsUserId && sessionId == student.sessionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nsUserId, sessionId);
    }

    public long getId() {
        return nsUserId;
    }

    public long getNsUserId() {
        return nsUserId;
    }

    public long getSessionId() {
        return sessionId;
    }
}
