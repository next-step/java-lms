package nextstep.courses.domain.session.student;

import java.util.Objects;

public class Student {

    private Long sessionId;
    private Long userId;

    public Student(Long sessionId, Long userId) {
        this.sessionId = sessionId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(sessionId, student.sessionId) && Objects.equals(userId, student.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, userId);
    }
}
