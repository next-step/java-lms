package nextstep.payments.domain;

import nextstep.courses.domain.session.Tuition;

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

    public Payment(Long sessionId, Long nsUserId, Long amount) {
        this(null, sessionId, nsUserId, amount);
    }

    public Payment(String id, Long sessionId, Long nsUserId, Long amount) {
        validateSessionId(sessionId);
        validateNsUserId(nsUserId);
        validateAmount(amount);
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    private void validateSessionId(Long sessionId) {
        if (sessionId == null) {
            throw new IllegalArgumentException("결제한 강의 아이디는 필수입니다.");
        }
    }

    private void validateNsUserId(Long nsUserId) {
        if (nsUserId == null) {
            throw new IllegalArgumentException("사용자 아이디는 필수입니다.");
        }
    }

    private void validateAmount(Long amount) {
        if (amount == null) {
            throw new IllegalArgumentException("결제 금액은 필수입니다.");
        }
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    public Long getAmount() {
        return amount;
    }

}
