package nextstep.courses.domain;


public class Student {
    private Long sessionId;

    private Long userId;

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
}
