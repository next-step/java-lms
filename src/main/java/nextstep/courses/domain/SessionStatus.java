package nextstep.courses.domain;

public enum SessionStatus {

    PREPARING, RECRUITING, FINISHED;

    public boolean isNotRecruiting() {
        return !this.equals(RECRUITING);
    }
}
