package nextstep.sessions.repository;

import java.util.List;
import java.util.Optional;

import nextstep.sessions.domain.data.type.ApprovalType;
import nextstep.sessions.domain.data.type.SelectionType;
import nextstep.sessions.domain.data.vo.Registration;

public interface RegistrationRepository {

    List<Registration> findAllRegistrations(int sessionId);

    void saveRegistration(int sessionId, Registration registration);

    Optional<Registration> findRegistrationByRegistrationId(int registrationId);

    void updateSelectionType(int registrationId, SelectionType selectionType);

    void updateApprovalType(int registrationId, ApprovalType approvalType);

    void deleteRegistration(int registrationId);
}
