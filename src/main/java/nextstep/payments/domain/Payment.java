package nextstep.payments.domain;

import java.time.LocalDateTime;

public class Payment {
    private String id;

    // 결제한 강의 아이디
    private Long sessionId;

    // 결제한 사용자 아이디
    private Long nsUserId;

    // 결제 금액
    private Long sessionFee;

    private LocalDateTime createdAt;

    public Payment() {
    }

    public Payment(String id, Long sessionId, Long nsUserId, Long sessionFee) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.sessionFee = sessionFee;
        this.createdAt = LocalDateTime.now();
    }

    public Long getSessionFee() {
        return this.sessionFee;
    }

    public String getId() {
        return id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getNsUserId() {
        return nsUserId;
    }
}
