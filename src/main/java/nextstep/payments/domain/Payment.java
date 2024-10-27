package nextstep.payments.domain;

import java.time.LocalDateTime;

public class Payment {

    private static final String MATCH_AMOUNT_MESSAGE = "결제 금액이 일치하지 않습니다.";

    private Long id;
    private Long sessionId;
    private Long nsUserId;
    private int amount;

    private LocalDateTime createdAt;

    public Payment(Long id, Long sessionId, Long nsUserId, int amount) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    public void checkMatchAmount(int amount) {
        if (this.amount != amount) {
            throw new IllegalArgumentException(MATCH_AMOUNT_MESSAGE);
        }
    }
}
