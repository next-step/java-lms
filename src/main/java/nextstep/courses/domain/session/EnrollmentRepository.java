package nextstep.courses.domain.session;

import java.util.List;

public interface EnrollmentRepository {
    void save(EnrolledStudent enrolledStudent);

    List<EnrolledStudent> findBySessionId(Long sessionId);

    void saveApplication(EnrollmentApplication application);

    void updateApplication(EnrollmentApplication application);
}
