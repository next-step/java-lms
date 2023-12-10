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

    public Payment(Long amount) {
        this("", 0L, 0L, amount);
    }

    public Payment() {
        this("", 0L, 0L, 0L);
    }

    public boolean isSameAmount(long amount) {
        return this.amount == amount;
    }
}
