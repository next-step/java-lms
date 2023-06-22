package nextstep.courses.domain;


public class Student {
    private Long sessionId;

    private Long userId;

    private ApprovalStatus approvalStatus;

    public Student(Long sessionId, Long userId, ApprovalStatus approvalStatus) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.approvalStatus = approvalStatus;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }
}
