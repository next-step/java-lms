package nextstep.courses.repository;

import nextstep.courses.domain.Enrollment;

public interface EnrollmentRepository {
    Long save(Enrollment enrollment);

    Enrollment findById(Long id);
}
