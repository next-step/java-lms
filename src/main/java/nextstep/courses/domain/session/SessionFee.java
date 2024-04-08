package nextstep.courses.domain.session;

public class SessionFee {

    private final Long id;
    private final Long sessionId;
    private final Long fee;

    public SessionFee(Long id, Long sessionId, Long fee) {
        this.id = id;
        this.sessionId = sessionId;
        this.fee = fee;
    }

    public boolean matchFee(Long amount) {
        return fee.equals(amount);
    }

    public Long get() {
        return fee;
    }
}
