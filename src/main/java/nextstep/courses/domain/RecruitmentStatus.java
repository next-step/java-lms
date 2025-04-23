package nextstep.courses.domain;

public enum RecruitmentStatus {
    NOT_RECRUITING,
    RECRUITING;

    public boolean isRecruiting() {
        return this == RECRUITING;
    }
}
