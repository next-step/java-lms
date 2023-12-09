package nextstep.courses.domain.enrollment;

import java.util.Optional;

public interface EnrollmentRepository {
    int save(Enrollment enrollment);
    Optional<Enrollment> findById(Long id);
}
