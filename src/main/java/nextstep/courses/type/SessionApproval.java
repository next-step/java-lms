package nextstep.courses.type;

public enum SessionApproval {
    WAIT,
    APPROVAL,
    CANCEL;

    public boolean isWait() {
        return this == WAIT;
    }

    public boolean isApproval() {
        return this == APPROVAL;
    }

    public boolean isCancel() {
        return this == CANCEL;
    }
}
