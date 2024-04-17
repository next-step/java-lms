package nextstep.courses.domain;

public enum RecruitmentState {

    RECRUITING,
    NOT_RECRUITING;

    public boolean isRecruiting() {
        return this == RECRUITING;
    }
}
