package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class EnrollmentCandidate {
    private final Long sessionId;
    private final Long nsUserId;
    private final Payment payment;
    private EnrollmentStatus status;
    private LocalDateTime approvedAt;
    private Long approvedBy;

    public EnrollmentCandidate(Long sessionId, Long nsUserId, Payment payment) {
        this(sessionId, nsUserId, payment, EnrollmentStatus.PENDING, LocalDateTime.now(), null);
    }

    public EnrollmentCandidate(Long sessionId, Long nsUserId, Payment payment, EnrollmentStatus status, LocalDateTime approvedAt, Long approvedBy) {
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.payment = payment;
        this.status = status;
        this.approvedAt = approvedAt;
        this.approvedBy = approvedBy;
    }

    public void approve(Long adminId) {
        if (!status.isPending()) {
            throw new IllegalStateException("대기 중인 신청만 승인 가능합니다.");
        }
        this.status = EnrollmentStatus.APPROVED;
        this.approvedAt = LocalDateTime.now();
        this.approvedBy = adminId;
    }

    public void cancel() {
        if (!status.isPending()) {
            throw new IllegalStateException("대기 중인 신청만 취소 가능합니다.");
        }
        this.status = EnrollmentStatus.CANCELLED;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    public Payment getPayment() {
        return payment;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public LocalDateTime getApprovedAt() {
        return approvedAt;
    }

    public Long getApprovedBy() {
        return approvedBy;
    }
}
