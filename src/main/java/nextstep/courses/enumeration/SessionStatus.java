package nextstep.courses.enumeration;

public enum SessionStatus {

    READY("준비중"),
    REGISTERING("모집중"),
    FINISH("종료");

    private final String value;

    SessionStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
