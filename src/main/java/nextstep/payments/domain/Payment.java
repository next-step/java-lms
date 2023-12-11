package nextstep.payments.domain;

import java.time.LocalDateTime;

public class Payment {

    private String id;

    // 결제한 강의 아이디
    private Long sessionId;

    // 결제한 사용자 아이디
    private Long nsUserId;

    // 결제 금액
    private Long amount;

    private LocalDateTime createdAt;

    public Payment(Long amount) {
        this.amount = amount;
    }

    public boolean isMatch(Long price) {
        return this.amount.equals(price);
    }
}
