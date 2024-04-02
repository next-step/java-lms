package nextstep.courses.infrastructure.dto;

public class LearnerDto {

    private Long sessionId;
    private Long userId;

    public LearnerDto(Long sessionId, Long userId) {
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
