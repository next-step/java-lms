package nextstep.session.domain;

import java.util.Optional;

public interface EnrollmentRepository {
	int save(Enrollment enrollment);
	Optional<Enrollment> findById(long id);
	int update(Enrollment imageInfo);
	int deleteById(long id);
}
