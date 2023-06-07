package nextstep.courses.domain.session;

import java.util.Objects;

public class Student {
    private final Long sessionId;

    private final Long userId;

    public Student(Long sessionId, Long userId) {
        this.sessionId = sessionId;
        this.userId = userId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(sessionId, student.sessionId) && Objects.equals(userId, student.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, userId);
    }
}
