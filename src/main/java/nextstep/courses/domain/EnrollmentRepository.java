package nextstep.courses.domain;

import java.util.Optional;

public interface EnrollmentRepository {
    long save(Enrollment enrollment);

    Optional<Enrollment> findById(Long id);
}
