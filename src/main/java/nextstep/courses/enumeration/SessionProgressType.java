package nextstep.courses.enumeration;

public enum SessionProgressType {

    READY("준비중"),
    IN_PROGRESS("진행중"),
    FINISH("종료");

    private final String value;

    SessionProgressType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
