package nextstep.payments.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    private Long id;

    // 결제한 강의 아이디
    private Long sessionId;

    // 결제한 사용자 아이디
    private Long nsUserId;

    // 결제 금액
    private BigDecimal amount;

    private LocalDateTime createdAt;

    public Payment() {
    }

    public static Payment init(Long sessionId, Long nsUserId, BigDecimal amount) {
        return new Payment(0L, sessionId, nsUserId, amount);
    }

    private Payment(Long id, Long sessionId, Long nsUserId, BigDecimal amount) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }
}
