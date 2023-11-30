package nextstep.courses.domain;

public enum SessionState {
    PREPARING, RECRUITING, END;

    public boolean isNotRecruiting() {
        return SessionState.RECRUITING != this;
    }


}
