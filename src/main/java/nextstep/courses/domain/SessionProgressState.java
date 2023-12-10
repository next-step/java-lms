package nextstep.courses.domain;

public enum SessionProgressState {
    PREPARING, RECRUITING, PROGRESSING, END;

    public boolean isNotRecruiting() {
        return SessionProgressState.RECRUITING != this;
    }


}
