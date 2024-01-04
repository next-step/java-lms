package nextstep.courses.infrastructure;

import nextstep.courses.domain.Student;
import nextstep.courses.domain.session.Session;

import java.util.List;

public interface SessionDAO {
    Long save(Session session);

    Session findById(Long id);

    List<Student> findStudentsBySessionId(Long sessionId);

    int saveStudent(Student nsUserSession);

    int updateStudent(Student nsUserSession);

    int updateSessionUserNumber(Session session);
}
