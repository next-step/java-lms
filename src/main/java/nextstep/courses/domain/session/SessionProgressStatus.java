package nextstep.courses.domain.session;

public enum SessionProgressStatus {
    READY, IN_PROGRESS, COMPLETE;

    public boolean isAbleToApply() {
        return this == READY || this == IN_PROGRESS;
    }
}
