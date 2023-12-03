package nextstep.payments.domain;

import nextstep.session.domain.Session;

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

    private Session session;

    public Payment() {
    }

    public Payment(Long sessionId, Long nsUserId, Long amount, LocalDateTime createdAt, Session session) {
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.amount = amount;
        this.createdAt = createdAt;
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public boolean validateSameAmount(Long amount) {
        return this.amount.equals(amount);
    }
}
