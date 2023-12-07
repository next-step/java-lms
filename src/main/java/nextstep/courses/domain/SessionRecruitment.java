package nextstep.courses.domain;

public enum SessionRecruitment {
    OPEN, CLOSE;

    public boolean isRecruitment() {
        return this == OPEN;
    }
}
