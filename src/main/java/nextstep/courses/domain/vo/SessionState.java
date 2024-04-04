package nextstep.courses.domain.vo;

public enum SessionState {
    PREPARATION,
    RECRUITING,
    FINISHED
    ;

    public boolean isRecruting() {
        return this == RECRUITING;
    }
}
