package nextstep.courses.domain.enrollment;

import java.util.List;

public interface EnrollmentRepository {

    int save(Enrollment enrollment);

    Enrollment findById(Long id);

    List<Enrollment> findBySessionId(Long sessionId);
}
