package nextstep.courses.domain.registration;

import java.util.List;

public interface RegistrationRepository {
    int save(Registration registration);

    Registration findById(Long id);

    List<Registration> findBySessionId(Long sessionId);

    List<Registration> findApprovedBySessionId(Long sessionId);

    int countBySessionId(Long sessionId);

   Registration findBySessionIdAndUserId(Long id, Long nsUserId);

  void delete(Long sessionId, Long studentId);
}
