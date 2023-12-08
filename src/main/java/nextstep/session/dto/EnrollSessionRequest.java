package nextstep.session.dto;

public class EnrollSessionRequest {

    private Long sessionId;

    private long fee;

    private String userId;

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
