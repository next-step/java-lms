package nextstep.courses.domain;

public enum SessionProgressStatus {
    READY("READY"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED");

    private final String status;

    private SessionProgressStatus(final String status) {
        this.status = status;
    }

    public boolean isInProgress() {
        return this == IN_PROGRESS;
    }
}
