package nextstep.courses.domain;

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
}
