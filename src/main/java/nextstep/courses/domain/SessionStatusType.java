package nextstep.courses.domain;

public enum SessionStatusType {
    PREPARE("준비중"),
    RECRUITING("모집중"),
    END("종료");

    String status;

    SessionStatusType(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
