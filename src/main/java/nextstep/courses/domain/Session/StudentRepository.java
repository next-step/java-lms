package nextstep.courses.domain.Session;

import java.util.List;

public interface StudentRepository {
    int save(Student student);
    List<Student> findBySessionId(Long sessionId);
}
