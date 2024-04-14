package nextstep.courses.infrastructure.engine;

import nextstep.courses.infrastructure.entity.SessionCoverImageEntity;

public interface SessionCoverImageRepository {

    int save(SessionCoverImageEntity entity);

    SessionCoverImageEntity findById(Long id);

}
