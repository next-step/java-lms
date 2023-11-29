package nextstep.lms.domain;

public enum SessionStatus {
    PREPARING("준비중"),
    RECRUITING("모집중"),
    COMPLETED("종료");

    private String status;

    private SessionStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

}
