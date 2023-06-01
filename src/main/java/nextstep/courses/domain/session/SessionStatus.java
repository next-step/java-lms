package nextstep.courses.domain.session;

public enum SessionStatus {

    PREPARING("준비중"),
    ENROLLING("모집중"),
    FINISHED("종료");

    private final String status;

    SessionStatus(String status) {
        this.status = status;
    }

    public boolean isEnrolling() {
        return this == ENROLLING;
    }

    public boolean isPreparing() {
        return this == PREPARING;
    }

    public boolean isFinished() {
        return this == FINISHED;
    }
}
