package nextstep.courses.domain;

public enum SessionStatus {
    PREPARING("준비중"),
    ENROLLING("모집중"),
    FINISH("마감");

    private final String status;

    SessionStatus(String status) {
        this.status = status;
    }

    public boolean isEnrolling() {
        return this == ENROLLING;
    }
}
