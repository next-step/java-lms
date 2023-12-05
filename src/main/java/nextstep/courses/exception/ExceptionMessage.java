package nextstep.courses.exception;

public enum ExceptionMessage {

    NOT_VALID_HEIGHT("이미지 높이가 유효하지 않습니다."),
    NOT_VALID_WIDTH("이미지 너비가 유효하지 않습니다."),
    NOT_VALID_RATIO("이미지 비율이 유효하지 않습니다"),
    EXCEED_LIMITED_VOLUME("이미지 용량이 제한된 크기를 넘었습니다"),
    CAN_NOT_APPLY("현재 수강신청을 할 수 없습니다."),
    EXCEED_ATTENDEES("해당 강의는 모집인원을 초과했습니다."),
    NEGATIVE_NUMBER("값을 음수로 설정할 수 없습니다."),
    NEGATIVE_OR_ZERO_NUMBER("값을 음수 혹은 0으로 설정할 수 없습니다."),
    PAYMENT_AMOUNT_NOT_EQUAL("지불된 가겨과 강의 금액이 일치하지 않습니다."),
    ALREADY_TAKING_SESSION("이미 수강 중인 강의입니다."),
    ;

    public static final String DELIMITER = " - ";

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
