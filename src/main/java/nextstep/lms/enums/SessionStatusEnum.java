package nextstep.lms.enums;

public enum SessionStatusEnum {
    PREPARING("준비중"),
    RECRUITING("모집중"),
    COMPLETED("종료");

    private String status;

    SessionStatusEnum(String status) {
        this.status = status;
    }
}
