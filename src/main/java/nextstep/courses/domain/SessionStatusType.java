package nextstep.courses.domain;

public enum SessionStatusType {
    READY("준비중"),
    OPEN("모집중"),
    CLOSED("종료");

    private final String status;

    SessionStatusType(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public boolean isOpen() {
        return this == OPEN;
    }
}
