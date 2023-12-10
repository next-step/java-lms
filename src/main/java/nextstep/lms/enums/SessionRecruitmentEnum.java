package nextstep.lms.enums;

public enum SessionRecruitmentEnum {
    NON_RECRUITMENT("비모집중"),
    RECRUITING("모집중");

    private String value;

    SessionRecruitmentEnum(String value) {
        this.value = value;
    }

    public boolean isRecruiting() {
        return this == RECRUITING;
    }
}
