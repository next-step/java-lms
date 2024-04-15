package nextstep.courses.infrastructure.engine;

import nextstep.courses.domain.enrollment.SessionStudent;

import java.util.List;

public interface SessionStudentRepository {

    int save(SessionStudent student);

    List<SessionStudent> findAllBySessionId(Long sessionId);

}
