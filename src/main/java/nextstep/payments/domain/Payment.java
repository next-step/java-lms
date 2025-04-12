package nextstep.payments.domain;

import java.time.LocalDateTime;

public class Payment {
    private final String id;
    private final Long sessionId;
    private final Long nsUserId;
    private final Long amount;

    private final LocalDateTime createdAt;

    public Payment(String id, Long sessionId, Long nsUserId, Long amount) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    public boolean isAmountEqual(int price) {
        return amount == price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id.equals(payment.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
