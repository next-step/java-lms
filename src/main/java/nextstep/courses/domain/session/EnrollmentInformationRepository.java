package nextstep.courses.domain.session;

public interface EnrollmentInformationRepository {
    int save(EnrollmentInformation enrollmentInformation);
    EnrollmentInformation findBySessionIdAndUserId(Long SessionId, Long userId);
}
