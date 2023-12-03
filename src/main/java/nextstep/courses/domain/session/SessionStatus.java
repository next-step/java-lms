package nextstep.courses.domain.session;

public enum SessionStatus {

    PREPARING, RECRUITING, FINISHED;

    public boolean isNotRecruiting() {
        return !this.equals(RECRUITING);
    }
}
