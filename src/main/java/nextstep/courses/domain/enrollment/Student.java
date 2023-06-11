package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.Session;

import java.util.Objects;

public class Student {

    private final Long sessionId;
    private final Long nsUserId;

    public Student(Long sessionId, Long nsUserId) {
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
    }

    public void enroll(Session session) {
        session.enroll(this);
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
