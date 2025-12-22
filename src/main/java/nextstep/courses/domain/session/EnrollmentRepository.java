package nextstep.courses.domain.session;

import java.util.List;

public interface EnrollmentRepository {
    void save(EnrollmentCandidate candidate);

    List<EnrollmentCandidate> findBySessionId(Long sessionId);

//    void saveCandidate(EnrollmentCandidate candidate);

    void update(EnrollmentCandidate candidate);
}
