package nextstep.payments.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Payment {
    private String id;

    // 결제한 강의 아이디
    private Long sessionId;

    // 결제한 사용자 아이디
    private Long nsUserId;

    // 결제 금액
    private Long amount;

    private LocalDateTime createdAt;

    public Payment() {
    }

    public Payment(String id, Long sessionId, Long nsUserId, Long amount) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof Payment))
            return false;
        Payment payment = (Payment) object;
        return Objects.equals(id, payment.id) && Objects.equals(sessionId, payment.sessionId) && Objects.equals(nsUserId, payment.nsUserId) && Objects.equals(amount, payment.amount) && Objects.equals(createdAt, payment.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionId, nsUserId, amount, createdAt);
    }
}
