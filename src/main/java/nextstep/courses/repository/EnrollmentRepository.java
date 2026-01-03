package nextstep.courses.repository;

import nextstep.courses.domain.Enrollment;

import java.util.List;

public interface EnrollmentRepository {
    Enrollment save(Enrollment enrollment);

    void update(Enrollment enrollment);

    Enrollment findById(long id);

    List<Enrollment> findBySessionId(long sessionId);
}
