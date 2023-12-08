package nextstep.courses.domain.session.repository;

import nextstep.courses.domain.session.Session;

import java.util.Optional;

public interface SessionRepository {

    Optional<Session> findBy(Long sessionId);
}
