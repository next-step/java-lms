package nextstep.courses.domain;

public enum SessionStatus {
    PREPARING, RECRUITING, ENDED;

    public boolean isRecruiting() {
        return this == RECRUITING;
    }

}
