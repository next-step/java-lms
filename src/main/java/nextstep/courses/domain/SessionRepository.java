package nextstep.courses.domain;

import java.util.Optional;

public interface SessionRepository {
    Optional<Session> findBy(long sessionId, SessionUsers sessionUsers);

    void updateCountBy(int userCount, long sessionId);
}
