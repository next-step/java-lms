package nextstep.courses.domain.session;

import java.util.List;

public interface EnrollmentRepository {
    void save(EnrolledStudent enrolledStudent);

    List<EnrolledStudent> findBySessionId(Long sessionId);

    void saveCandidate(EnrollmentCandidate candidate);

    void updateCandidate(EnrollmentCandidate candidate);
}
