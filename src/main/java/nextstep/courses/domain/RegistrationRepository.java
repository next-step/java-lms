package nextstep.courses.domain;

import java.util.Optional;

public interface RegistrationRepository {

  Optional<Registration> findById(Long id);

  void save(Registration registration);
}
