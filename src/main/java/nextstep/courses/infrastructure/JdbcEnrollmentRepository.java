package nextstep.courses.infrastructure;

import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.EnrollmentRepository;
import nextstep.courses.domain.Enrollments;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {
    @Override
    public void save(Enrollment enrollment) {
    }

    @Override
    public Optional<Enrollments> findByUserId(Long userId) {
        return Optional.empty();
    }
}
