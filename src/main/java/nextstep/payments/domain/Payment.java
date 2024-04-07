package nextstep.payments.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class Payment {

    private static final Random random = new Random();

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
        return new Payment(UUID.randomUUID().toString(), random.nextLong(), nsUserId, 10000L);
    }

    public boolean isSameAmount(long amount) {
        return this.amount == amount;
    }

    public boolean isSameSessionId(int sessionId) {
        return this.sessionId == sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) && Objects.equals(sessionId, payment.sessionId) && Objects.equals(nsUserId, payment.nsUserId) && Objects.equals(amount, payment.amount) && Objects.equals(createdAt, payment.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionId, nsUserId, amount, createdAt);
    }
}
