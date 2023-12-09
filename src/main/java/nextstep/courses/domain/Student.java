package nextstep.courses.domain;

import java.util.Objects;

public class Student {

    private Long id;
    private final Long sessionId;
    private final Long studentId;

    public Student(Long sessionId, Long studentId) {
        this(null, sessionId, studentId);
    }

    public Student(Long id, Long sessionId, Long studentId) {
        this.id = id;
        this.sessionId = sessionId;
        this.studentId = studentId;
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
        return Objects.equals(sessionId, student.sessionId)
                && Objects.equals(studentId, student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, studentId);
    }
}
