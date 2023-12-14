package nextstep.courses.type;

public enum ApprovalStatus {
    NONE, APPROVAL, CANCEL;

    public boolean isApproval() {
        return this.equals(APPROVAL);
    }

    public boolean isCancel() {
        return this.equals(CANCEL);
    }
}
