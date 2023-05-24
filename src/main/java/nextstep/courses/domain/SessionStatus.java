package nextstep.courses.domain;

public enum SessionStatus {
    PREPARING("준비중"),
    RECRUITING("모집중"),
    END("종료");

    private final String status;

    SessionStatus(String status) {
        this.status = status;
    }

    public boolean canEnroll() {
        return this == RECRUITING;
    }
}
