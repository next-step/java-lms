package nextstep.courses.domain.session.repository;

import nextstep.courses.domain.session.FreeSession;
import nextstep.courses.domain.session.PaySession;
import nextstep.courses.domain.session.Session;

import java.util.Optional;

public interface SessionRepository {

    Optional<PaySession> findPaySessionById(Long sessionId);
    Optional<FreeSession> findFreeSessionById(Long sessionId);
}
