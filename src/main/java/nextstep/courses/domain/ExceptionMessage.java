package nextstep.courses.domain;

public enum ExceptionMessage {
    COVER_IMAGE_RATIO("강의의 커버 이미지는 3:2이여야 합니다."),
    COVER_IMAGE_BYTE_SIZE("강의의 커버 이미지 파일의 크기는 1MB 이하이여야 합니다."),
    COVER_IMAGE_LEAST_SIZE("강의의 커버 이미지의 최소 너비는 300px, 최소 높이는 200px 입니다."),
    DURATION_RANGE("강의 기간이 알맞지 않습니다."),
    ENROLLMENT_SIZE("수강 인원이 꽉 찼습니다."),
    IMAGE_EXTENSION_NOT_FOUND_TYPE("지원하지 않는 확장자입니다."),
    SESSIONS_NOT_FOUND_SESSION("강의를 찾을 수 없습니다."),
    SESSION_NOT_MATCH_AMOUNT_OF_PAY("결제 금액이 수강료와 일치하지 않습니다."),
    SESSION_STATUS_NOT_ENROLLING("현재 강의는 모집중이지 않습니다."),
    SESSION_PAYMENT_NEED_PRICE("유료강의는 가격이 0 초과이여야 합니다."),
    SESSION_PAYMENT_NOT_FOUND_TYPE("존재 하지 않는 타입입니다.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
