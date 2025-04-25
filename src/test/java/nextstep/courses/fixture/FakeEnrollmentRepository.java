package nextstep.courses.fixture;

import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.EnrollmentRepository;
import nextstep.courses.domain.EnrollmentStatus;
import nextstep.courses.domain.Enrollments;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class FakeEnrollmentRepository implements EnrollmentRepository {

    private final AtomicLong idGenerator;
    private final Map<Long, Enrollment> enrollments;

    public FakeEnrollmentRepository() {
        idGenerator = new AtomicLong();
        enrollments = new ConcurrentHashMap<>();
    }

    @Override
    public void save(Enrollment enrollment) {
        if (enrollment.getId() == null) {
            long newId = idGenerator.getAndIncrement();
            enrollment.assignId(newId);
        }

        enrollments.put(enrollment.getId(), enrollment);
    }

    @Override
    public Enrollments findByUserId(Long userId) {
        return new Enrollments(
                enrollments.values().stream()
                        .filter(enrollment -> enrollment.getStudent().getId().equals(userId))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Enrollments findBySessionId(Long sessionId) {
        return new Enrollments(
                enrollments.values().stream()
                        .filter(enrollment -> enrollment.getSession().getId().equals(sessionId))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Enrollments findByStatus(EnrollmentStatus enrollmentStatus) {
        return new Enrollments(
                enrollments.values().stream()
                        .filter(enrollment -> enrollment.getStatus().equals(enrollmentStatus))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Enrollments findBySessionIdAndStatus(Long sessionId, EnrollmentStatus enrollmentStatus) {
        return new Enrollments(
                enrollments.values().stream()
                        .filter(enrollment -> enrollment.getSession().getId().equals(sessionId))
                        .filter(enrollment -> enrollment.getStatus().equals(enrollmentStatus))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Optional<Enrollment> findById(Long enrollmentId) {
        return Optional.ofNullable(enrollments.get(enrollmentId));
    }

    @Override
    public void updateStatus(Enrollment enrollment) {
        findById(enrollment.getId())
                .ifPresent(value -> value.updateStatus(enrollment.getStatus()));
    }
}
