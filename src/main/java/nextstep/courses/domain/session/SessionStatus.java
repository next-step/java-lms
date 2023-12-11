package nextstep.courses.domain.session;

public enum SessionStatus {
    PREPARING, IN_PROGRESS, FINISHED;

    public boolean isNotInProcess() {
        return !this.equals(IN_PROGRESS);
    }
}
