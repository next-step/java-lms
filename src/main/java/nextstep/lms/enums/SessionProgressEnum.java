package nextstep.lms.enums;

public enum SessionProgressEnum {
    PREPARING("준비중"),
    PROGRESSING("진행중"),
    COMPLETED("종료");

    private String value;

    SessionProgressEnum(String value) {
        this.value = value;
    }
}
