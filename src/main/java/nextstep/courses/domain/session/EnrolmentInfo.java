package nextstep.courses.domain.session;

public class EnrolmentInfo {

    private Long sessionId;
    private Long userId;
    private Long payAmount;

    public EnrolmentInfo(Long sessionId, Long userId, Long payAmount) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.payAmount = payAmount;
    }

    public Long sessionId() {
        return sessionId;
    }

    public Long userId() {
        return userId;
    }

    public boolean isNotSameAmount(Long amount) {
        return !this.payAmount.equals(amount);
    }
}
