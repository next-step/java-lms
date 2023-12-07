package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class SessionEnroll {
    private final Long id;
    private final Long sessionId;
    private final Long studentId;
    private final String paymentId;
    private final LocalDateTime createdAt;

    public SessionEnroll(Long sessionId, Long studentId, String paymentId) {
        this(0L, sessionId, studentId, paymentId, LocalDateTime.now());
    }

    public SessionEnroll(Long id, Long sessionId, Long studentId, String paymentId) {
        this(id, sessionId, studentId, paymentId, LocalDateTime.now());
    }

    public SessionEnroll(Long id, Long sessionId, Long studentId, String paymentId, LocalDateTime createdAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.studentId = studentId;
        this.paymentId = paymentId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
