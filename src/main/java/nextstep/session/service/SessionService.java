package nextstep.session.service;

import nextstep.session.domain.Session;

public interface SessionService {

    long save(Session session);

    Session findById(long sessionId);
}
