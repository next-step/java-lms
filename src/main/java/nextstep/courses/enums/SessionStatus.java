package nextstep.courses.enums;

public enum SessionStatus {
    RECRUITING, PREPARING, END;

    public boolean enrollable() {
        return this == RECRUITING;
    }
}
