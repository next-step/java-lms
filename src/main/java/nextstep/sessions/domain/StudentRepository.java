package nextstep.sessions.domain;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    int save(Student student);

    List<Student> findBySessionId(long SessionId);

    Optional<Student> findById(Long studentId);
}
