package nextstep.payments.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Payment {
    private Long id;

    // 결제한 강의 아이디
    private Long sessionId;

    // 결제한 사용자 아이디
    private Long nsUserId;

    // 결제 금액
    private Long amount;

    private LocalDateTime createdAt;

    public Payment() {
    }

    public Payment(Long id, Long sessionId, Long nsUserId, Long amount) {
        this(id, sessionId, nsUserId, amount, LocalDateTime.now());
    }

    public Payment(Long id, Long sessionId, Long nsUserId, Long amount, LocalDateTime createdAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public boolean verifySessionId(long other) {
        return this.sessionId == other;
    }

    public boolean verifyUserId(long other) {
        return this.nsUserId == other;
    }

    public boolean verifySessionAmount(long other) {
        return this.amount == other;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Payment payment = (Payment) other;
        return Objects.equals(id, payment.id) && Objects.equals(sessionId, payment.sessionId) && Objects.equals(nsUserId, payment.nsUserId) && Objects.equals(amount, payment.amount) && Objects.equals(createdAt, payment.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionId, nsUserId, amount, createdAt);
    }
}
