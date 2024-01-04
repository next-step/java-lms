package nextstep.courses.domain;

public enum EnrollmentStatus {
    WAITING("신청 대기"),
    APPROVED("승인"),
    REJECTED("취소");
    private String displayName;

    EnrollmentStatus(String displayName) {
        this.displayName = displayName;
    }

    public static EnrollmentStatus get(boolean approvalRequired) {
        if (approvalRequired) {
            return WAITING;
        }
        return APPROVED;
    }

    public boolean isApproved() {
        return this == APPROVED;
    }
}
