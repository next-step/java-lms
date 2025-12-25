package nextstep.courses.repository;

import nextstep.courses.domain.Enrollment;

import java.util.List;

public interface EnrollmentRepository {
    int save(Enrollment enrollment);
    List<Enrollment> findBySessionId(Long sessionId);
}
