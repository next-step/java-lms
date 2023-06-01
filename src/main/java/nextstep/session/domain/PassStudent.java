package nextstep.session.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassStudent that = (PassStudent) o;
        return Objects.equals(sessionId, that.sessionId) && Objects.equals(nsUserId, that.nsUserId) && Objects.equals(isPass, that.isPass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, nsUserId, isPass);
    }
}
