package nextstep.courses.domain;

public enum EnrollmentStatus {
    APPLIED("신청"),
    SELECTED("선발"),
    APPROVED("승인"),
    CANCELED("취소"),
    ;

    private final String status;

    EnrollmentStatus(String status) {
        this.status = status;
    }

    public boolean isSelected() {
        return this == EnrollmentStatus.SELECTED;
    }

    public boolean isApplied() {
        return this == EnrollmentStatus.APPLIED;
    }

    public boolean isApproved() {
        return this == EnrollmentStatus.APPROVED;
    }
}
