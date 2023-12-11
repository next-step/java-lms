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

    public static Payment paidOf(String id, long sessionId, long nsUserId, Long amount) {
        return new Payment(id, sessionId, nsUserId, amount);
    }
    public static Payment freeOf(String id, long sessionId, long nsUserId) {
        return new Payment(id, sessionId, nsUserId, 0L);
    }

    public Payment() {

    }
    public Payment(String id, Long sessionId, Long nsUserId, Long amount) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    public boolean isNotSamePrice(Long sessionFee) {
        return !sessionFee.equals(amount);
    }
}
