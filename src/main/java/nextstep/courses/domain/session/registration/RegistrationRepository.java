package nextstep.courses.domain.session.registration;

import java.util.List;

public interface RegistrationRepository {
	List<Registration> findRegistrationsBySessionId(Long sessionId);
	int save(Registration registration);
}
