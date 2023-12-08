package nextstep.session.dto;

public class EnrollSessionRequest {

    private Long sessionId;

    private long fee;

    private String userId;

    public EnrollSessionRequest(Long sessionId, long fee, String userId) {
        this.sessionId = sessionId;
        this.fee = fee;
        this.userId = userId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public long getFee() {
        return fee;
    }

    public String getUserId() {
        return userId;
    }
}
