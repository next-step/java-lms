package nextstep.courses.domain.model;

public enum RecruitmentStatus {
    ON, OFF;

    public boolean isNotSupport() {
        return this != RecruitmentStatus.ON;
    }
}
