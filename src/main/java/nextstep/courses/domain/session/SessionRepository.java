package nextstep.courses.domain.session;

import nextstep.courses.entity.SessionEntity;

import java.util.List;

public interface SessionRepository {
    Long save(SessionEntity sessionEntity);

    SessionEntity findById(Long id);

    List<SessionEntity> findAllByCourseId(Long courseId);

    void delete(Long sessionId);
}
