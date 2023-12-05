package nextstep.courses.domain.repository;

import nextstep.courses.domain.entity.Enrollment;
import nextstep.courses.domain.entity.NsCourse;
import nextstep.courses.domain.entity.NsSession;
import nextstep.users.domain.NsUser;

public interface EnrollmentRepository {

    int save(NsCourse nsCourse,
             NsUser nsUser,
             NsSession nsSession);

    Enrollment findById(Long id);
}
