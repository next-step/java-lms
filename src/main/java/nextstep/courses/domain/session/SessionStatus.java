package nextstep.courses.domain.session;

public enum SessionStatus {
    READY, APPLYING, COMPLETE;

    public boolean isApplying() {
        return this == SessionStatus.APPLYING;
    }
}
