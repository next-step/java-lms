package nextstep.courses.domain;

public interface EnrollmentRepository {
    int save(Enrollment enrollment);

    Enrollment findById(Long sessionId, String userId);
}
