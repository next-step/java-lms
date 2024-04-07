package nextstep.courses.exception;

public enum SessionExceptionMessage {

    CAPACITY_EXCEED("강의 최대 수강 인원을 초과했습니다."),
    PAYMENT_MISMATCH("결제한 금액과 수강료가 일치하지 않습니다.")
    ;

    private final String message;

    SessionExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
