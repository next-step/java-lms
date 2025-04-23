package nextstep.courses.domain;

import java.util.Optional;

public interface EnrollmentRepository {
    void save(Enrollment enrollment);

    Enrollments findByUserId(Long userId);

    Enrollments findBySessionId(Long sessionId);

    Enrollments findByStatus(EnrollmentStatus enrollmentStatus);

    Enrollments findBySessionIdAndStatus(Long sessionId, EnrollmentStatus enrollmentStatus);

    Optional<Enrollment> findById(Long enrollmentId);

    void updateStatus(Enrollment enrollment);
}
