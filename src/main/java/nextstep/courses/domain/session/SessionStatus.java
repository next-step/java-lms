package nextstep.courses.domain.session;

public enum SessionStatus {
    PREPARING("준비중"),
    RECRUITING("모집중"),
    END("종료");

    SessionStatus(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
