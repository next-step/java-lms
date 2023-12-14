package nextstep.courses.domain.session.enums;

public enum RecruitingStatus {

    RECRUITING_ON("모집중"),
    RECRUITING_OFF("비모집중");

    private String description;

    RecruitingStatus(String description) {
        this.description = description;
    }

    public static boolean isNotRecruiting(RecruitingStatus recruitingStatus) {
        return RECRUITING_OFF.equals(recruitingStatus);
    }
}
