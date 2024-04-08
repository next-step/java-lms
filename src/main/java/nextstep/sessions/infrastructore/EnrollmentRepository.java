package nextstep.sessions.infrastructore;

import nextstep.sessions.domain.Enrollment;

import java.util.List;

public interface EnrollmentRepository {
    long save(Enrollment enrollment);

    void saveAll(List<Enrollment> enrollments);

    List<Enrollment> findBySessionId(Long sessionId);

    List<Enrollment> findByNsUserId(Long nsUserId);

    List<Enrollment> findBy(Long sessionId, Long nsUserId);

    int update(Enrollment enrollment);
}
