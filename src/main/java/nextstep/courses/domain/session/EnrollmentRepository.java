package nextstep.courses.domain.session;

import java.util.Set;

public interface EnrollmentRepository {
    Set<Student> findEnrolledStudentsBySessionId(long sessionId);

    void save(long sessionId, Student student);

    void updateEnrollmentStatus(long sessionId, Student student);

}