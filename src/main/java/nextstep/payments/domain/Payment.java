package nextstep.payments.domain;

import java.time.LocalDateTime;
import nextstep.courses.domain.Money;

public class Payment {
    private String id;

    // 결제한 강의 아이디
    private Long sessionId;

    // 결제한 사용자 아이디
    private Long nsUserId;

    // 결제 금액
    private Money amount;

    private LocalDateTime createdAt;

    public Payment() {
    }

    public Payment(String id, Long sessionId, Long nsUserId, Long amount) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.amount = new Money(amount);
        this.createdAt = LocalDateTime.now();
    }

    public boolean isEqualAmount(Money money) {
        return amount.equal(money);
    }
}
