package nextstep.payments.domain;

import nextstep.money.Money;

import java.time.LocalDateTime;
import java.util.Objects;

public class Payment {
    private Long id;

    // 결제한 강의 아이디
    private Long sessionId;

    // 결제한 사용자 아이디
    private Long nsUserId;

    // 결제 금액
    private Money amount;

    private LocalDateTime createdAt;

    private PaymentState state;

    public Payment() {
    }

    public Payment(Long sessionId, Long nsUserId, long amount) {
        this(0L, sessionId, nsUserId, Money.wons(amount), LocalDateTime.now(), PaymentState.PENDING);
    }

    public Payment(Long sessionId, Long nsUserId, long amount, LocalDateTime createdAt) {
        this(0L, sessionId, nsUserId, Money.wons(amount), createdAt, PaymentState.PENDING);
    }

    public Payment(Long sessionId, Long nsUserId, Money amount) {
        this(0L, sessionId, nsUserId, amount, LocalDateTime.now(), PaymentState.PENDING);
    }

    public Payment(Long sessionId, Long nsUserId, Money amount, LocalDateTime createdAt) {
        this(0L, sessionId, nsUserId, amount, createdAt, PaymentState.PENDING);
    }

    public Payment(Long id, Long sessionId, Long nsUserId, Money amount, LocalDateTime createdAt, PaymentState state) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.amount = amount;
        this.createdAt = createdAt;
        this.state = state;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Payment payment = (Payment) other;
        return Objects.equals(id, payment.id) && Objects.equals(sessionId, payment.sessionId) && Objects.equals(nsUserId, payment.nsUserId) && Objects.equals(amount, payment.amount) && Objects.equals(createdAt, payment.createdAt) && state == payment.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionId, nsUserId, amount, createdAt, state);
    }
}
