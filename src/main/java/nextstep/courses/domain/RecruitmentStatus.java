package nextstep.courses.domain;

public enum RecruitmentStatus {

    RECRUITING,
    NOT_RECRUITING;

    public boolean isSame(RecruitmentStatus recruitmentStatus) {
        return this == recruitmentStatus;
    }
}
