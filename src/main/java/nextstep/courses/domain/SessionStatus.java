package nextstep.courses.domain;

public enum SessionStatus {
    READY("준비중"),
    OPEN("모집중"),
    CLOSED("종료");

    private final String status;

    SessionStatus(String status) {
        this.status = status;
    }

    public boolean canEnrollment() {
        return this == OPEN;
    }
}
