package nextstep.courses.domain;

public enum SessionProgressState {
    PREPARING, RECRUITING, PROGRESSING, END;

    public boolean isRecruiting() {
        return SessionProgressState.RECRUITING == this;
    }


}
