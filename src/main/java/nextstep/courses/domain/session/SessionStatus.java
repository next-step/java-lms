package nextstep.courses.domain.session;

public enum SessionStatus {
    PREPARING, GATHERING, END;

    public boolean isEnrollPossibleStatus() {
        return this == GATHERING;
    }
}
