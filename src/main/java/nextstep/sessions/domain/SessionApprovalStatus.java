package nextstep.sessions.domain;

public enum SessionApprovalStatus {
    WAITING("대기중"),
    APPROVAL("승인"),
    CANCEL("취소");

    private final String status;

    SessionApprovalStatus(String status) {
        this.status = status;
    }

    public boolean isApproval() {
        return this.status.equals(APPROVAL.status);
    }
}
