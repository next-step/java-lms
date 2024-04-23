package nextstep.courses.domain.Session;

public enum SessionStatus {
    READY, APPLYING, COMPLETE;

    public boolean isApplying() {
        return this == SessionStatus.APPLYING;
    }
}
