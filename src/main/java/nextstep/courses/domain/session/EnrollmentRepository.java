package nextstep.courses.domain.session;

public interface EnrollmentRepository {
    Enrollment save(Enrollment enrollment);

    Enrollment findById(Long id);

    Enrollment update(Enrollment enrollment);
}
