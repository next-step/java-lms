package nextstep.courses.domain.registration;

public enum EnrollmentStatus {
    PENDING("PENDING", "수강 승인 대기"),
    APPROVED("APPROVED", "수강 승인 완료"),
    CANCELED("CANCELED", "수강 취소");

    private String code;
    private String description;

    EnrollmentStatus(String code, String description) {
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
