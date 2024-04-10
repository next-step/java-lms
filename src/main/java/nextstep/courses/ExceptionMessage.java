package nextstep.courses;

public enum ExceptionMessage {
    INVALID_COVER_IMAGE("잘못된 커버 이미지 입니다."),
    INVALID_PERIOD("잘못된 시작일과 종료일입니다.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
