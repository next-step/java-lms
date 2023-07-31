package nextstep.courses.domain.registration;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepository {

  Optional<Registration> findById(Long id);

  List<Registration> findBySessionId(Long sessionId);

  Long save(Registration registration);
}
