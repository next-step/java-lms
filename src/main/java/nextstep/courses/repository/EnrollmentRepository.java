package nextstep.courses.repository;

import nextstep.courses.domain.Enrollment;

import java.util.List;

public interface EnrollmentRepository {
    Long save(Enrollment enrollment);

    Enrollment findById(Long id);

    List<Enrollment> findBySessionId(Long sessionId);
}
