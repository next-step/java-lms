package nextstep.courses.domain;

public enum SessionStatus {

    PREPARING("준비중"),
    ENROLLING("모집중"),
    FINISHED("종료");

    private final String status;

    SessionStatus(String status) {
        this.status = status;
    }

    public String statusValue() {
        return status;
    }

    public boolean isEnrollmentPossible() {
        if (this != ENROLLING) {
            throw new IllegalArgumentException("the current session is not in the enrolling status");
        }
        return true;
    }

    public boolean isPreparing() {
        return this == PREPARING;
    }
}
