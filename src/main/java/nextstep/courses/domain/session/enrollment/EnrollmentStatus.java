package nextstep.courses.domain.session.enrollment;

public enum EnrollmentStatus {
    PENDING_APPROVAL("승인대기"),
    ENROLLED("수강신청"),
    WAITING("대기"),
    CANCELLED("취소");

    private final String description;

    EnrollmentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public EnrollmentStatus approve() {
        if (this == EnrollmentStatus.PENDING_APPROVAL) {
            return EnrollmentStatus.ENROLLED;
        }
        throw new IllegalStateException("승인 대기 상태의 수강신청만 승인할 수 있습니다.");
    }

    public EnrollmentStatus cancel() {
        if (this == EnrollmentStatus.PENDING_APPROVAL ||
                this == EnrollmentStatus.ENROLLED) {
            return EnrollmentStatus.CANCELLED;
        }
        throw new IllegalStateException("승인 대기 또는 수강신청 상태만 취소할 수 있습니다.");
    }

    public boolean isPendingApproval() {
        return this == EnrollmentStatus.PENDING_APPROVAL;
    }

    public boolean isEnrolled() {
        return this == EnrollmentStatus.ENROLLED;
    }

    public boolean isCancelled() {
        return this == EnrollmentStatus.CANCELLED;
    }
}