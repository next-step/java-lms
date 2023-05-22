package nextstep.courses.domain.repository;

import nextstep.courses.domain.Enrollment;

public interface EnrollmentRepository {
    long save(Enrollment course);
    Enrollment findById(Long id);
    int update(Enrollment course);
    int delete(Long id);
}
