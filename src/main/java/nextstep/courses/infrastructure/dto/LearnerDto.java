package nextstep.courses.infrastructure.dto;

public class LearnerDto {

    private Long sessionId;
    private Long userId;
    private ApplyStatus applyStatus;

    public LearnerDto(Long sessionId, Long userId) {
        this(sessionId, userId, ApplyStatus.PENDING);
    }

    public LearnerDto(Long sessionId, Long userId, ApplyStatus applyStatus) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.applyStatus = applyStatus;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public ApplyStatus getApplyStatus() {
        return applyStatus;
    }
}
