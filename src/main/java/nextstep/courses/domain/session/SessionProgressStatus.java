package nextstep.courses.domain.session;

public enum SessionProgressStatus {
    PREPARING("강의 준비중"),
    IN_PROGRESS("강의중"),
    FINISHED("강의 종료"),
    EMPTY("정보 없음");

    private String displayName;

    SessionProgressStatus(String displayName) {
        this.displayName = displayName;
    }

    public boolean isFinished() {
        return this == FINISHED;
    }

    public static SessionProgressStatus get(String value) {
        if (value == null) {
            return EMPTY;
        }
        return valueOf(value);
    }
}
