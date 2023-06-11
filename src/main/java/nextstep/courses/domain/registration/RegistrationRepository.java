package nextstep.courses.domain.registration;

import java.util.Optional;

public interface RegistrationRepository {

  Optional<Registration> findById(Long id);

  void save(Registration registration);
}
