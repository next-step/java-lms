package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.SessionRegisterDetails;

import java.util.Optional;

public interface SessionRegisterDetailsRepository {
    int save(SessionRegisterDetails sessionRegisterDetails);

    Optional<SessionRegisterDetails> findById(long sessionRegisterDetailsId);
}
