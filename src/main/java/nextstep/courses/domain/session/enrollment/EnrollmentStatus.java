package nextstep.courses.domain.session.enrollment;

public enum EnrollmentStatus {
    ENROLLED("수강신청"),
    WAITING("대기"),
    CANCELLED("취소");

    private final String description;

    EnrollmentStatus(String description) {
        this.description = description;
    }
}
