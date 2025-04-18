package nextstep.courses.domain;

import java.util.Optional;

public interface EnrollmentRepository {
    void save(Enrollment enrollment);

    Optional<Enrollments> findByUserId(Long userId);

    Optional<Enrollments> findBySessionId(Long sessionId);
}
