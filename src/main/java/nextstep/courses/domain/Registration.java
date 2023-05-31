package nextstep.courses.domain;

public class Registration {
    private Long id;
    private Long userId;
    private Long sessionId;

    public Registration(){

    }

    public Registration(Long id, Long userId, Long sessionId) {
        this.id = id;
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
