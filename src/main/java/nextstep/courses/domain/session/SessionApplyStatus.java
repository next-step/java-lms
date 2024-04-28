package nextstep.courses.domain.session;

public enum SessionApplyStatus {
    NOT_APPLYING, APPLYING;

    public boolean isApplying() {
        return this == SessionApplyStatus.APPLYING;
    }
}
