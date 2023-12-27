package nextstep.session.domain;

import java.util.List;

public interface EnrollmentRepository {
    long save(Enrollment enrollment);

    Enrollment findById(Long id);

    List<Enrollment> findAllBySessionId(Long sessionId);

    void update(Enrollment enrollment);

    void delete(Enrollment enrollment);
}
