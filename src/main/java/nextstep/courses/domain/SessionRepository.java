package nextstep.courses.domain;

import java.util.List;

public interface SessionRepository {
    long save(Session session);

    Session findById(Long id);
}
