package nextstep.courses.domain.enrollment;

import java.util.Objects;

public class Student {

    private final Long nsUserId;
    private final Long sessionId;

    public Student(Long nsUserId, Long sessionId) {
        this.nsUserId = nsUserId;
        this.sessionId = sessionId;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(nsUserId, student.nsUserId)
                && Objects.equals(sessionId, student.sessionId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(nsUserId, sessionId);
    }
}
