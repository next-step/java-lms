package nextstep.payments.domain;

import java.time.LocalDateTime;

public class Payment {

    private String id;

    // 결제한 강의 아이디
    private Long sessionId;

    // 결제한 사용자 아이디
    private Long nsUserId;

    // 결제 금액
    private int amount;

    private LocalDateTime createdAt;

    public Payment() {
    }

    public Payment(String id,
                   Long sessionId,
                   Long nsUserId,
                   int amount) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Payments{" +
               "id=" + id +
               ", sessionId='" + sessionId + '\'' +
               ", nsUserId='" + nsUserId + '\'' +
               ", amount='" + amount + '\'' +
               ", createdAt=" + createdAt +
               '}';
    }
}
