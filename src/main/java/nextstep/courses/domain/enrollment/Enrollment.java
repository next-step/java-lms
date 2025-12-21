package nextstep.courses.domain.enrollment;

import java.util.Objects;

public class Enrollment {

    private final Long studentId;
    private final Long sessionId;

    public Enrollment(Long studentId, Long sessionId) {
        this.studentId = studentId;
        this.sessionId = sessionId;
    }

    public Long studentId() {
        return this.studentId;
    }

    public Long sessionId() {
        return this.sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Enrollment that = (Enrollment) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(sessionId, that.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, sessionId);
    }
}
