package nextstep.courses.exception;

public enum SessionExceptionMessage {

    CAPACITY_EXCEED("강의 최대 수강 인원을 초과했습니다."),
    PAYMENT_MISMATCH("결제한 금액과 수강료가 일치하지 않습니다."),
    UNSUPPORTED_IMAGE_FORMAT("지원하지 않는 이미지 형식입니다."),
    CANNOT_ENROLLMENT_SESSION_STATUS("해당 강의는 수강신청 불가능한 상태입니다."),
    INVALID_SESSION_DURATION("강의 시작일시와 종료일시가 유효하지 않습니다.")
    ;

    private final String message;

    SessionExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
