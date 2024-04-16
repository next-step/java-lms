package nextstep.courses.infrastructure.engine;

import nextstep.courses.domain.student.SessionStudent;

import java.util.List;

public interface SessionStudentRepository {

    int save(SessionStudent student);

    int[] updateAll(List<SessionStudent> students);

    List<SessionStudent> findAllBySessionId(Long sessionId);

}
