package nextstep.session.domain;

public class PassStudent {

    private Long sessionId;
    private Long nsUserId;
    private Boolean isPass;

    public PassStudent(Long sessionId, Long nsUserId, Boolean isPass) {
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.isPass = isPass;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    public Boolean getPass() {
        return isPass;
    }
}
