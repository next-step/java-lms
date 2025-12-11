package nextstep.courses.domain.session.policy.approval;

public enum ApprovalPolicy {
    AUTO,
    MANUAL;

    public boolean requiresApproval() {
        return this == MANUAL;
    }

    public boolean canAutoApprove() {
        return this == AUTO;
    }
}
