package nextstep.courses.infrastructure.engine;

import nextstep.courses.domain.student.SessionStudent;
import nextstep.courses.domain.student.SessionStudents;

public interface SessionStudentRepository {

    int save(SessionStudent student);

    int[] updateAll(SessionStudents students);

    SessionStudents findAllBySessionId(Long sessionId);

}
