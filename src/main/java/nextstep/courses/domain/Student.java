package nextstep.courses.domain;

import java.util.Objects;

public class Student {
    private final Long nsUserId;
    private final Long sessionId;

    private boolean approved;

    public Student(Long nsUserId, Long sessionId) {
        this(nsUserId, sessionId, true);
    }

    public Student(Long nsUserId, Long sessionId, boolean approved) {
        this.nsUserId = nsUserId;
        this.sessionId = sessionId;
        this.approved = approved;
    }

    public void approve() {
        this.approved = true;
    }

    public void disApprove() {
        this.approved = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return approved == student.approved && Objects.equals(nsUserId, student.nsUserId) && Objects.equals(sessionId, student.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nsUserId, sessionId, approved);
    }

    @Override
    public String toString() {
        return "Student{" +
                "nsUserId=" + nsUserId +
                ", sessionId=" + sessionId +
                ", approved=" + approved +
                '}';
    }
}
