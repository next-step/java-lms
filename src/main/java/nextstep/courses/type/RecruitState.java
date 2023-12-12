package nextstep.courses.type;

public enum RecruitState {
    RECRUITING,
    CLOSED;

    public boolean recruiting() {
        return this == RECRUITING;
    }
}
