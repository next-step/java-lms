package nextstep.courses.infrastructure;

import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.EnrollmentRepository;
import org.springframework.stereotype.Repository;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {
    @Override
    public void save(Enrollment enrollment) {

    }
}
