package nextstep.courses.repository;

import nextstep.courses.domain.Session;

import java.util.Optional;

public interface SessionRepository {

    int save(Session session);

    int updateTitle(String title, Long id);

    int delete(Long id);

    Optional<Session> findById(Long id);
}
