package nextstep.courses.domain.registration;

import java.util.Optional;
import nextstep.courses.domain.registration.Registration;

public interface RegistrationRepository {

  Optional<Registration> findById(Long id);

  void save(Registration registration);
}
