package nextstep.courses.domain.session.student;

import java.util.Objects;

public class Student {

    private Long id;
    private Long sessionId;
    private Long nsUserId;

    public Student(Long sessionId, Long nsUserId) {
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
    }

    public Student(Long id, Long sessionId, Long nsUserId) {
        this(sessionId, nsUserId);
        this.id = id;
    }

    public Long getSessionId() {
        return this.sessionId;
    }

    public Long getNsUserId() {
        return this.nsUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(sessionId, student.sessionId) && Objects.equals(nsUserId, student.nsUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionId, nsUserId);
    }
}
