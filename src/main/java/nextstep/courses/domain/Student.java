package nextstep.courses.domain;

import java.util.Objects;

public class Student {
    private final Long sessionId;
    private final Long nsUserId;

    public Student(Long sessionId, Long nsUserId) {
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(sessionId, student.sessionId) && Objects.equals(nsUserId, student.nsUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, nsUserId);
    }
}
