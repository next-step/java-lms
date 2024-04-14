package nextstep.courses.infrastructure.engine;

import nextstep.courses.infrastructure.entity.SessionStudentEntity;

import java.util.List;

public interface SessionStudentRepository {

    int save(SessionStudentEntity entity);

    List<SessionStudentEntity> findAllBySessionId(Long sessionId);

}
