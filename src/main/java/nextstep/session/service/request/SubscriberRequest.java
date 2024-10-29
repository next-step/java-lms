package nextstep.session.service.request;

public class SubscriberRequest {

    private Long sessionId;
    private Long userId;

    public SubscriberRequest(Long sessionId, Long userId) {
        this.sessionId = sessionId;
        this.userId = userId;
    }

    public SessionFindRequest toSessionFindRequest() {
        return new SessionFindRequest(this.sessionId);
    }

    public Long getUserId() {
        return userId;
    }
}
