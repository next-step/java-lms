package nextstep.courses.domain;

public enum SessionStatusType {
    READY(false),
    RECRUITMENT(true),
    END(false);

    private final boolean canApply;

    SessionStatusType(boolean canApply) {
        this.canApply = canApply;
    }

    public boolean isRecruitment() {
        return this.canApply;
    }
}
