package nextstep.courses.enumeration;

public enum SessionRecruitStatus {

    RECRUITING("모집중"),
    NOT_RECRUITING("비모집중")
            ;

    private final String value;

    SessionRecruitStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
