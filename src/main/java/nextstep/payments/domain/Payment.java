package nextstep.payments.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {

    private String id;
    private Long sessionId; // 결제한 강의 ID
    private Long nsUserId; // 결제한 사용자 ID
    private Long amount; // 결제 금액
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

    public static Payment findByUserId(Long nsUserId) {
        return new Payment(UUID.randomUUID().toString(), UUID.randomUUID().node(), nsUserId, 10000L);
    }

    public boolean isSameAmount(long amount) {
        return this.amount == amount;
    }
}
