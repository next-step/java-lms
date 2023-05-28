package nextstep.courses.domain;

public enum SessionStatus {
    PREPARING, RECRUITING, END;

    public boolean isNotRecruiting() {
        return this != RECRUITING;
    }


}
