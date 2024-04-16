package nextstep.payments.domain;

import java.time.LocalDateTime;

public class Payment {

    private String id;
    private Long sessionId;
    private Long nsUserId;
    private Money amount;
    private LocalDateTime createdAt;

    public Payment() {
    }

    public Payment(final int amount) {
        this(null, null, null, new Money(amount));
    }

    public Payment(final String id, final Long sessionId, final Long nsUserId, final Money amount) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    public Money amount() {
        return this.amount.copyOf();
    }
}
