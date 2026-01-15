package nextstep.courses.domain.enroll;

import java.util.Objects;

public class Enrollment {
    private Long userId;
    private Long sessionId;

    public Enrollment(Long userId, Long sessionId) {
        this.userId = userId;
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment enrollment = (Enrollment) o;
        return Objects.equals(userId, enrollment.userId) && Objects.equals(sessionId, enrollment.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, sessionId);
    }
}
