package nextstep.courses.domain;

import java.util.Objects;

public class Enrollment {
    private final Long id;
    private final Long studentId;
    private final Long sessionId;

    public  Enrollment(Long studentId, Long sessionId) {
        this(1L, studentId, sessionId);
    }

    public Enrollment(Long id, Long studentId, Long sessionId) {
        this.id = id;
        this.studentId = studentId;
        this.sessionId = sessionId;
    }

    public boolean sameSession(Long id) {
        return this.sessionId.equals(id);
    }

    public void validateBelongsTo(Long sessionId) {
        if (!sameSession(sessionId)) {
            throw new IllegalArgumentException("신청하고자 하는 강의가 아닙니다.");
        }
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(id, that.id) && Objects.equals(studentId, that.studentId) && Objects.equals(sessionId, that.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentId, sessionId);
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", sessionId=" + sessionId +
                '}';
    }
}
