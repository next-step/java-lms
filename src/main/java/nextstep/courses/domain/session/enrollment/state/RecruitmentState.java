package nextstep.courses.domain.session.enrollment.state;

public enum RecruitmentState {
    NON_RECRUITING("비모집중"),
    RECRUITING("모집중");

    RecruitmentState(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
