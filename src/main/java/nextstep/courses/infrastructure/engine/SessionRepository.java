package nextstep.courses.infrastructure.engine;

import nextstep.courses.domain.Session;
import nextstep.courses.infrastructure.entity.SessionEntity;

public interface SessionRepository {

    int save(SessionEntity entity);

    SessionEntity findById(Long id);

}
