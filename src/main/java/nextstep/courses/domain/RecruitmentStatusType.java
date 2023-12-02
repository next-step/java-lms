package nextstep.courses.domain;

public enum RecruitmentStatusType {
    RECRUITING("모집중"),
    NOT_RECRUITING("비모집중");

    private final String description;

    RecruitmentStatusType(String description) {
        this.description = description;
    }
}
