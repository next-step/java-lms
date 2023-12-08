package nextstep.session.domain;

public enum SessionStatus {

    PREPARING, RECRUITING, END;

    public boolean checkTakingLecture() {
        return this.equals(RECRUITING);
    }
}
