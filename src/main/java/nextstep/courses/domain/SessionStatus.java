package nextstep.courses.domain;

public enum SessionStatus {
    READY("READY"),
    OPENED("OPENED"),
    CLOSED("CLOSED");

    private final String status;

    private SessionStatus(final String status) {
        this.status = status;
    }

    public boolean isOpened() {
        return this == OPENED;
    }
}
