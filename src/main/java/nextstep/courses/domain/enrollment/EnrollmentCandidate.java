package nextstep.courses.domain.enrollment;

public class EnrollmentCandidate {
    private final Long sessionId;
    private final Long nsUserId;
    private EnrollmentStatus status;

    public EnrollmentCandidate(Long sessionId, Long nsUserId) {
        this(sessionId, nsUserId, EnrollmentStatus.PENDING);
    }

    public EnrollmentCandidate(Long sessionId, Long nsUserId, EnrollmentStatus status) {
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.status = status;
    }

    public void approve(Long adminId) {
        if (!status.isPending()) {
            throw new IllegalStateException("대기 중인 신청만 승인 가능합니다.");
        }
        this.status = EnrollmentStatus.APPROVED;
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

    public EnrollmentStatus getStatus() {
        return status;
    }
}
