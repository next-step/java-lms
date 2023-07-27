package nextstep.sessions.domain.students;

import java.util.Optional;

public interface StudentRepository {

  Optional<Student> findById(Long id);

  Students findAllBySessionId(Long sessionId);

  void update(Student student);

  void save(Student student);
}
