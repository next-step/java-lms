package nextstep.courses.domain.session;

import nextstep.courses.domain.image.SessionImage;

import java.util.Optional;

public interface SessionRepository {
    int save(Session session);

    Session findBySessionId(Long id);
}
