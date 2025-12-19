package nextstep.courses.enrollment.service.repository;

import nextstep.courses.enrollment.domain.Enrollment;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository {

    Enrollment save(Enrollment enrollment);
}
