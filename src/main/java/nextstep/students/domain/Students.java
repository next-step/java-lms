package nextstep.students.domain;

public class Students {

    private Long sessionId;
    private Long nsUserId;

    public Students(Long sessionId, Long nsUserId) {
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getNsUserId() {
        return nsUserId;
    }
}


