package nextstep.courses.domain;

public enum SessionProgressStatus {
    READY("준비중"),
    IN_PROGRESS("진행중"),
    END("종료"),
    ;

    private final String status;

    SessionProgressStatus(String status) {
        this.status = status;
    }

    public boolean enableProgress() {
        return this != END;
    }
}
