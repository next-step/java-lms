package nextstep.courses.domain.session.enrollment.state;

public enum RecruitmentState {
    PREPARING("준비중"),
    RECRUITING("모집중"),
    END("종료");

    RecruitmentState(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}