package nextstep.courses;

public enum ExceptionMessage {
    INVALID_COVER_IMAGE("잘못된 커버 이미지 입니다."),
    INVALID_PERIOD("잘못된 시작일과 종료일입니다."),
    INVALID_MAX_NUMBER_OF_ENROLLMENT_AND_FEE("잘못된 최대 수강 인원과 수강료입니다."),
    DUPLICATE_SESSION_ENROLL("중복된 수강 신청입니다."),
    INVALID_SESSION_STATUS_FOR_ENROLL("수강 신청은 모집중인 경우에만 가능합니다."),
    SESSION_ALREADY_FULL("강의에 빈자리가 없습니다."),
    INVALID_PAYMENT_FOR_ENROLL("결제한 금액과 수강료가 일치하지 않습니다.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
