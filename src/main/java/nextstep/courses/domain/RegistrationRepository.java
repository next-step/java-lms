package nextstep.courses.domain;

import java.util.List;

public interface RegistrationRepository {
  int save(Registration registration);

  Registration findById(Long id);

  List<Registration> findByUserId(Long userId);

  List<Registration> findBySessionId(Long sessionId);
}
