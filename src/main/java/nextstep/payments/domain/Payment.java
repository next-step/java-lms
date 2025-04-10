package nextstep.payments.domain;

import java.time.LocalDateTime;

public class Payment {
    private final String id;

    // 결제한 강의 아이디
    private final Long sessionId;

    // 결제한 사용자 아이디
    private final Long nsUserId;

    // 결제 금액
    private final Long amount;

    private final LocalDateTime createdAt;


    public Payment(String id, Long sessionId, Long nsUserId, Long amount) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    public Long getAmount() {
        return amount;
    }
}
