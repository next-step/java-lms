package nextstep.courses.domain.session;

public enum SessionStatus {
    PREPARING, RECRUITING, CLOSE;

    public boolean isRecruitable(){
        return this.equals(RECRUITING);
    }
}
