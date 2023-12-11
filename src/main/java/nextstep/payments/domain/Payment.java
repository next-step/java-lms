package nextstep.payments.domain;

import nextstep.courses.exception.PaymentException;

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

    public Payment() {
    }

    public Payment(String id, Long sessionId, Long nsUserId, Long amount) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    public boolean isEqualPaidFee(Long fee) {
        return amount.equals(fee);
    }

    public void isAbleToPayment(Long fee) {
        if(!isEqualPaidFee(fee)) {
            throw new PaymentException("수강료가 지불한 금액과 일치하지 않습니다.");
        }
    }
}
