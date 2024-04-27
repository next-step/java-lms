package nextstep.courses.domain.session.enrollment.state;

public enum ProgressState {
    PREPARING("준비중"),
    ONGOING("진행중"),
    END("종료");

    ProgressState(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
