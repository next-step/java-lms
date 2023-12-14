package nextstep.courses.domain.session;

public enum SessionProgressStatus {
    PREPARING("강의 준비중"),
    IN_PROGRESS("강의중"),
    FINISHED("강의 종료");

    private String displayName;

    SessionProgressStatus(String name) {
        this.displayName = name;
    }

    public boolean canRecuriting() {
        return this != FINISHED;
    }
}
