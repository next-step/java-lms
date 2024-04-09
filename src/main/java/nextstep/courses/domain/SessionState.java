package nextstep.courses.domain;

public enum SessionState {
    PREPARING,
    RECRUITING,
    TERMINATE;

    public boolean isRecruiting() {
        return this == RECRUITING;
    }
}
