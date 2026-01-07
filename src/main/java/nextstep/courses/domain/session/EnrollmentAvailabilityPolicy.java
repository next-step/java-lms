package nextstep.courses.domain.session;

public class EnrollmentAvailabilityPolicy {
    void validate(SessionProgress sessionProgress, EnrollmentStatus enrollmentStatus) {
        if (sessionProgress != SessionProgress.IN_PROGRESS && enrollmentStatus != EnrollmentStatus.OPEN) {
            throw new IllegalStateException("수강신청 불가");
        }
    }
}
