package nextstep.courses.domain.session;

public interface EnrollmentRepository {
    void save (Long sessionId, Long nsUserId);
}
