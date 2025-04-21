package nextstep.courses.fixture;

import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.EnrollmentRepository;
import nextstep.courses.domain.Enrollments;

import java.util.Optional;

public class FakeEnrollmentRepository implements EnrollmentRepository {
    @Override
    public void save(Enrollment enrollment) {
    }

    @Override
    public Optional<Enrollments> findByUserId(Long userId) {
        return Optional.of(new Enrollments());
    }

    @Override
    public Optional<Enrollments> findBySessionId(Long sessionId) {
        return Optional.of(new Enrollments());
    }
}
