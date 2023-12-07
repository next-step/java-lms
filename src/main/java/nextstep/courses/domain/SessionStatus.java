package nextstep.courses.domain;

public enum SessionStatus {
    PREPARE, PROGRESS, COMPLETE;

    public boolean isComplete() {
        return this == COMPLETE;
    }
}
