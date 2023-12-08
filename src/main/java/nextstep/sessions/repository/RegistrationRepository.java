package nextstep.sessions.repository;

import java.util.List;
import java.util.Optional;

import nextstep.sessions.domain.data.type.ApprovalType;
import nextstep.sessions.domain.data.type.SelectionType;
import nextstep.sessions.domain.data.vo.Registration;

public interface RegistrationRepository {

    List<Registration> findAllById(int sessionId);

    void save(int sessionId, Registration registration);

    Optional<Registration> findById(int registrationId);

    void updateSelectionType(int registrationId, SelectionType selectionType);

    void updateApprovalType(int registrationId, ApprovalType approvalType);

    void deleteById(int registrationId);
}
