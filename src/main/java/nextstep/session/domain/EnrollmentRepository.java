package nextstep.session.domain;

import java.util.List;

public interface EnrollmentRepository {
    void save(Enrollment enrollment);

    List<Enrollment> findAllBySessionId(Long sessionId);
}
