package nextstep.courses.domain.session;

public class EnrollmentAvailabilityPolicy {
    void validate(SessionProgress sessionProgress, SessionStstus sessionStstus) {
        if (sessionProgress != SessionProgress.IN_PROGRESS && sessionStstus != SessionStstus.OPEN) {
            throw new IllegalStateException("수강신청 불가");
        }
    }
}
