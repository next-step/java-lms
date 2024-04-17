package nextstep.courses.domain.session;

public enum SessionStatusEnum {
    PENDING,
    OPEN,
    CLOSED;

    public boolean isSessionOpened() {
        return this == OPEN;
    }

}
