package nextstep.courses.domain.registration;

public enum RegistrationStatus {
    PENDING("pending", "수강 승인 대기"),
    APPROVED("approved", "수강 승인 완료"),
    CANCELED("canceled", "수강 취소");

    private String code;
    private String description;

    RegistrationStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public boolean isPending() {
        return this == PENDING;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
