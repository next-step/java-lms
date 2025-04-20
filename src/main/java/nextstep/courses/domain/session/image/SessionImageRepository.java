package nextstep.courses.domain.session.image;

import nextstep.courses.entity.SessionImageEntity;

import java.util.List;

public interface SessionImageRepository {
    Long save(SessionImageEntity sessionImageEntity);

    List<SessionImageEntity> findAllBySessionId(Long sessionId);
}
