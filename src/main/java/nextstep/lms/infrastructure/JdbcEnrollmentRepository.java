package nextstep.lms.infrastructure;

import nextstep.lms.domain.Enrollment;
import nextstep.lms.domain.EnrollmentRepository;
import org.springframework.stereotype.Repository;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {
    @Override
    public void save(Enrollment enrollment) {

    }
}
