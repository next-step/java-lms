package nextstep.courses.domain.session;

public class EnrolmentInfo {

    private Long sessionId;
    private String nsUserId;
    private Long payAmount;

    public EnrolmentInfo(Long sessionId, String nsUserId, Long payAmount) {
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.payAmount = payAmount;
    }

    public Long sessionId() {
        return sessionId;
    }

    public String nsUserId() {
        return this.nsUserId;
    }

    public boolean isNotSameAmount(Long amount) {
        return !this.payAmount.equals(amount);
    }
}
