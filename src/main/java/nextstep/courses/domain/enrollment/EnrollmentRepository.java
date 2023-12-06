package nextstep.courses.domain.enrollment;

public interface EnrollmentRepository {
    int save(Enrollment enrollment);

    Enrollment findById(Long id);
}
