package nextstep.courses.domain.session;

public enum EnrollmentStatus {
    PENDING("대기"),
    APPROVED("승인"),
    REJECTED("취소"),
    ;

    private final String description;

    EnrollmentStatus(String description) {
        this.description = description;
    }

    public boolean isApproved() {
        return this == APPROVED;
    }

    public boolean isRejected() {
        return this == REJECTED;
    }
}
