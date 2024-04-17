package nextstep.courses.domain;

public enum RecruitStatus {

    RECRUIT,
    NOT_RECRUIT;

    public static RecruitStatus findByName(String name) {
        return RecruitStatus.valueOf(name.toUpperCase());
    }

    public boolean isRecruit() {
        return this.equals(RECRUIT);
    }


}
