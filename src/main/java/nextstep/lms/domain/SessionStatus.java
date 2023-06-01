package nextstep.lms.domain;

public enum SessionStatus {
    PREPARING("현재 준비중인 강의입니다."),
    OPEN("현재 모집중인 강의입니다."),
    FULL("현재 모집 마감된 강의입니다."),
    CLOSED("현재 종료된 강의입니다.");

    private final String message;

    SessionStatus(String message) {
        this.message = message;
    }

    public static void throwExceptionIfNotOpen(SessionStatus status) {
        if (status != OPEN) {
            throw new IllegalStateException(status.message);
        }
    }

    public String message() {
        return message;
    }

    public SessionStatus open() {
        if (this != SessionStatus.PREPARING) {
            throw new IllegalStateException("현재 준비중인 강의만 모집중으로 변경 가능합니다.");
        }
        return SessionStatus.OPEN;
    }
}
