package nextstep.courses.domain.Session;

public enum Status {
    READY, APPLYING, COMPLETE;

    public boolean isApplying() {
        return this == Status.APPLYING;
    }
}
