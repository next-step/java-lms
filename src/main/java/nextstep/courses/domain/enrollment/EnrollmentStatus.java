package nextstep.courses.domain.enrollment;

public enum EnrollmentStatus {
    WAITING("대기"),
    APPROVED("승인"),
    REJECTED("취소");

    private final String description;

    EnrollmentStatus(String description) {
        this.description = description;
    }
}
