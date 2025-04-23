package nextstep.courses.fixture;

import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.EnrollmentRepository;
import nextstep.courses.domain.EnrollmentStatus;
import nextstep.courses.domain.Enrollments;

public class FakeEnrollmentRepository implements EnrollmentRepository {
    @Override
    public void save(Enrollment enrollment) {
    }

    @Override
    public Enrollments findByUserId(Long userId) {
        return new Enrollments();
    }

    @Override
    public Enrollments findBySessionId(Long sessionId) {
        return new Enrollments();
    }

    @Override
    public Enrollments findByStatus(EnrollmentStatus enrollmentStatus) {
        return null;
    }

    @Override
    public Enrollments findBySessionIdAndStatus(Long sessionId, EnrollmentStatus enrollmentStatus) {
        return null;
    }
}
