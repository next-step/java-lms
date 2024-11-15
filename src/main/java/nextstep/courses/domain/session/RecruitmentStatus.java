package nextstep.courses.domain.session;

public enum RecruitmentStatus {
    NOT_RECRUITING("비모집중"),
    RECRUITING("모집중");

    private final String description;

    RecruitmentStatus(String description) {
        this.description = description;
    }

    public boolean isNotRecruiting() {
        return this == NOT_RECRUITING;
    }

}
