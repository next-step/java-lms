package nextstep.courses.infrastructure.engine;

import nextstep.courses.domain.student.SessionStudent;

import java.util.List;

public interface SessionStudentRepository {

    int save(SessionStudent student);

    List<SessionStudent> findAllBySessionId(Long sessionId);

}
