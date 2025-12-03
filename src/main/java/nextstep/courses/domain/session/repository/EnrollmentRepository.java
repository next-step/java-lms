package nextstep.courses.domain.session.repository;

import nextstep.courses.record.EnrollmentRecord;

import java.util.List;

public interface EnrollmentRepository {

    int save(Long id, Long seesionId, Long userId);

    List<EnrollmentRecord> findBySessionId(Long sessionId);

}
