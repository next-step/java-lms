package nextstep.courses;

public enum ExceptionMessage {
    INVALID_COVER_IMAGE("잘못된 커버 이미지 입니다."),
    INVALID_PERIOD("잘못된 시작일과 종료일입니다."),
    INVALID_MAX_NUMBER_OF_ENROLLMENT_AND_FEE("잘못된 최대 수강 인원과 수강료입니다."),
    SESSION_ENROLL_FAIL_MESSAGE("수강 신청을 할 수 없는 강의 입니다.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
