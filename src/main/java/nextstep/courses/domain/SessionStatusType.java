package nextstep.courses.domain;

public enum SessionStatusType {
    PREPARING,
    RECRUITING,
    CLOSE;

    public boolean canRegister() {
        return this == RECRUITING;
    }
}
