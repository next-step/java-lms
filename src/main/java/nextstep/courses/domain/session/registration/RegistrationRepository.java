package nextstep.courses.domain.session.registration;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepository {
	int save(Registration registration);
	Optional<Registration> findById(Long id);
	List<Registration> findAllBySessionId(Long sessionId);
}
