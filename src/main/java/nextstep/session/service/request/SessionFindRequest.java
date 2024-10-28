package nextstep.session.service.request;

public class SessionFindRequest {

    private final Long sessionId;

    public SessionFindRequest(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getSessionId() {
        return sessionId;
    }
}
