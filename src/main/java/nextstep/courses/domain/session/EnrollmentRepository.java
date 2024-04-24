package nextstep.courses.domain.session;

public interface EnrollmentRepository {
    int save(Enrollment enrollment);
    Enrollment findBySessionIdAndUserId(Long sessionId, Long userId);
}
