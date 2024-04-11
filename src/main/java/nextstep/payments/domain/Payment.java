package nextstep.payments.domain;

import nextstep.courses.domain.BaseEntity;

public class Payment extends BaseEntity {
    // 결제한 강의 아이디
    private Long sessionId;

    // 결제한 사용자 아이디
    private Long nsUserId;

    // 결제 금액
    private Long amount;

    public Payment() {
    }

    public Payment(String id, Long sessionId, Long nsUserId, Long amount) {
        this(Long.parseLong(id), sessionId, nsUserId, amount);
    }

    public Payment(Long id, Long sessionId, Long nsUserId, Long amount) {
        super(id);
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.amount = amount;
    }

    public boolean isSame(Long amount){
        return this.amount.equals(amount);
    }
}
