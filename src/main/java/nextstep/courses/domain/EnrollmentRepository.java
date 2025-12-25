package nextstep.courses.domain;

import java.util.List;

public interface EnrollmentRepository {
    int save(Enrollment enrollment);
    List<Enrollment> findBySessionId(Long sessionId);
}
