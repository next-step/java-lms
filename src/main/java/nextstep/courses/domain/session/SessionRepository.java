package nextstep.courses.domain.session;

import nextstep.courses.entity.SessionEntity;

public interface SessionRepository {
    int save(SessionEntity session);

    SessionEntity findById(Long sessionId);

    int updateNumberOfStudent(Long sessionId, int numberOfStudent);
}
