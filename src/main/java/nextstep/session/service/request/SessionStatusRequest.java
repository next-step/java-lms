package nextstep.session.service.request;

import nextstep.session.domain.SubscribeStatus;

public class SessionStatusRequest {

    private Long sessionId;
    private SubscribeStatus subscribeStatus;

    public SessionStatusRequest(Long sessionId, SubscribeStatus subscribeStatus) {
        this.sessionId = sessionId;
        this.subscribeStatus = subscribeStatus;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public SubscribeStatus getSubscribeStatus() {
        return subscribeStatus;
    }
}
