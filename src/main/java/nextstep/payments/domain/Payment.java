package nextstep.payments.domain;

import java.time.LocalDateTime;

public class Payment {
    private String id;
    private Long sessionId;
    private Long nsUserId;
    private Long amount; // 결제 금액
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
}
