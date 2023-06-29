package nextstep.courses.domain.registration;

import java.util.Objects;

public class Student {
    private final Long nsUserId;
    private final Long sessionId;
    private final boolean approved;

    public Student(Long nsUserId, Long sessionId) {
        this(nsUserId, sessionId, true);
    }

    public Student(Long nsUserId, Long sessionId, boolean approved) {
        this.nsUserId = nsUserId;
        this.sessionId = sessionId;
        this.approved = approved;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public boolean getStatus() {
        return approved;
    }

    public Student approve() {
        return new Student(this.nsUserId, this.sessionId, true);
    }

    public Student reject() {
        return new Student(this.nsUserId, this.sessionId, false);
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

    @Override
    public String toString() {
        return "Student{" +
                "nsUserId=" + nsUserId +
                ", sessionId=" + sessionId +
                '}';
    }
}
