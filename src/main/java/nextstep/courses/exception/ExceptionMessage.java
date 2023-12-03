package nextstep.courses.exception;

public enum ExceptionMessage {

    NOT_VALID_SIZE("이미지 크기가 유효하지 않습니다."),
    EXCEED_LIMITED_VOLUME("이미지 용량이 제한된 크기를 넘었습니다"),
    NOT_CORRECT_PERIOD("강의 기간이 올바른 형식이 아닙니다."),
    CAN_NOT_APPLY("현재 수강신청을 할 수 없습니다."),
    ;

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
