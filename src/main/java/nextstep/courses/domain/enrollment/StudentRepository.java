package nextstep.courses.domain.enrollment;

import java.util.List;

public interface StudentRepository {
    int save(Student student);

    List<Students> findBySessionId(Long sessionId);
}
