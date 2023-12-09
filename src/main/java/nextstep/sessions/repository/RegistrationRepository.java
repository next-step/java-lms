package nextstep.sessions.repository;

import java.util.List;
import java.util.Optional;

import nextstep.sessions.domain.data.registration.Registration;

public interface RegistrationRepository {

    List<Registration> findAllById(int sessionId);

    void save(Registration registration);

    Optional<Registration> findById(int registrationId);

    void updateSelectionType(Registration registration);

    void updateApprovalType(Registration registration);

    void deleteById(int registrationId);
}
