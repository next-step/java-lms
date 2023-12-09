package nextstep.lms.domain;

public class Student {
    private final Long userId;
    private final Long sessionId;

    public Student(Long userId, Long sessionId) {
        this.userId = userId;
        this.sessionId = sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getSessionId() {
        return sessionId;
    }
}
