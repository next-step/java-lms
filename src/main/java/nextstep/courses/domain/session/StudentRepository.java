package nextstep.courses.domain.session;

import java.util.List;

public interface StudentRepository {
    int save(Student student);

    List<Student> findBySessionId(Long sessionId);
}
