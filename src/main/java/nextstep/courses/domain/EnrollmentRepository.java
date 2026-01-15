package nextstep.courses.domain;

import java.util.List;
import nextstep.courses.domain.enrollment.Enrollment;

public interface EnrollmentRepository {

    Long save(Enrollment enrollment);

    List<Enrollment> findBySessionId(Long sessionId);
}
