package nextstep.courses.domain;

public class Enrollment {
    private final Long studentId;
    private final Long sessionId;

    public Enrollment(Long studentId, Long sessionId) {
        this.studentId = studentId;
        this.sessionId = sessionId;
    }
}
