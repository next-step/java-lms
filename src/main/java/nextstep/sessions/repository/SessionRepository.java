package nextstep.sessions.repository;

import nextstep.sessions.domain.data.Session;
import nextstep.sessions.domain.data.vo.Registration;

public interface SessionRepository {

    Session findById(long sessionId);

    void save(Registration register);
}
