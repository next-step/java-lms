package nextstep.courses.infrastructure.dto;

public class LearnerDto {

    private Long sessionId;
    private Long userId;
    private boolean isAccepted;

    public LearnerDto(Long sessionId, Long userId) {
        this(sessionId, userId, false);
    }

    public LearnerDto(Long sessionId, Long userId, boolean isAccepted) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.isAccepted = isAccepted;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public boolean isAccepted() {
        return isAccepted;
    }
}
