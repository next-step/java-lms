package nextstep.sessions.domain;

import java.util.Optional;

public interface SessionRegisterDetailsRepository {

    int save(SessionRegisterDetails sessionRegisterDetails);

    Optional<SessionRegisterDetails> findById(long sessionRegisterDetailsId);

}
