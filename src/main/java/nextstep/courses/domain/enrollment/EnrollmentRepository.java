package nextstep.courses.domain.enrollment;

import org.springframework.stereotype.Repository;

public interface EnrollmentRepository {

    int save(Enrollment enrollment);

    Enrollment findById(Long id);

    int update(Enrollment enrollment);
}
