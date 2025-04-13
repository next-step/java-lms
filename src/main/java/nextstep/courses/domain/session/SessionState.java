package nextstep.courses.domain.session;

public enum SessionState {
    PREPARING,
    RECRUITING,
    CLOSED;

    public boolean canRecruit(){
        return this == RECRUITING;
    }
}
