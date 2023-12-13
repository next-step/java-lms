package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public interface RegistrationRepository {
    int save(Registration registration);
    List<NsUser> findParticipantsBySessionId(Long sessionId);
}
