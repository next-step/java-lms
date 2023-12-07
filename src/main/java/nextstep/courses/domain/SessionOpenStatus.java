package nextstep.courses.domain;

public enum SessionOpenStatus {
    OPENED("OPENED"),
    CLOSED("CLOSED");

    private final String status;

    private SessionOpenStatus(final String status) {
        this.status = status;
    }

    public boolean isOpened() {
        return this == OPENED;
    }
}
