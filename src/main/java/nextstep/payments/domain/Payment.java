package nextstep.payments.domain;

import java.time.LocalDateTime;

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

    public Payment(String id, Long sessionId, Long nsUserId, Money amount) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    public boolean isSamePaymentAmount(Money tuitionFee){
        return amount.equals(tuitionFee);
    }

    public int getAmount() {
        return amount.getValue();
    }
}
