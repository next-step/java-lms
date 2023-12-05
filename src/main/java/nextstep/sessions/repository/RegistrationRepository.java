package nextstep.sessions.repository;

import java.util.List;

import nextstep.sessions.domain.data.vo.Registration;

public interface RegistrationRepository {
    
    List<Registration> findAllRegistrations(int sessionId);

    void saveRegistration(int sessionId, Registration registration);

}
