package nextstep.courses.domain.session;

public enum SessionStatusEnum {
    PENDING,
    OPEN,
    CLOSED;

    public boolean isSessionOpened() {
        if (this != OPEN) {
            return false;
        }

        return true;
    }

}
