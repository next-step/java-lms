package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {
    @Override
    public void save(Enrollment enrollment) {

    }
}
