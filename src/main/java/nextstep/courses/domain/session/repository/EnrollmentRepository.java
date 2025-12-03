package nextstep.courses.domain.session.repository;

import nextstep.courses.domain.session.Enrollment;
import nextstep.courses.record.EnrollmentRecord;

import java.util.List;

public interface EnrollmentRepository {

    int save(Enrollment enrollment);

    List<EnrollmentRecord> findBySessionId(Long sessionId);

}
