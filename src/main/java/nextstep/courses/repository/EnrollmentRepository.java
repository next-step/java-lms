package nextstep.courses.repository;

import nextstep.courses.domain.Enrollment;

public interface EnrollmentRepository {
    int save(Enrollment enrollment);

    Enrollment findById(Long id);
}
