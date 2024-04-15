package nextstep.courses.exception;

public enum CourseExceptionMessage {

    CAPACITY_EXCEED("강의 최대 수강 인원을 초과했습니다."),
    INVALID_CAPACITY("강의 최소 수강인원 제한을 만족하지 못했습니다."),
    PAYMENT_MISMATCH("결제한 금액과 수강료가 일치하지 않습니다."),
    UNSUPPORTED_IMAGE_FORMAT("지원하지 않는 이미지 형식입니다."),
    CANNOT_ENROLLMENT_SESSION_STATUS("해당 강의는 수강신청 불가능한 상태입니다."),
    INVALID_SESSION_DURATION("강의 시작일시와 종료일시가 유효하지 않습니다."),
    INVALID_SESSION_TYPE("강의 유형이 유효하지 않습니다."),
    INVALID_SESSION_PROGRESS_STATUS("강의 진행 상태가 유효하지 않습니다."),
    INVALID_SESSION_RECRUITMENT_STATUS("강의 모집 상태가 유효하지 않습니다."),
    NOT_MATCH_SESSION_ENROLLMENT("매칭되는 신청 유형이 없습니다.")
    ;

    private final String message;

    CourseExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
