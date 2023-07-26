package nextstep.sessions.domain.students;

import java.util.Optional;

public interface StudentRepository {

  Optional<Student> findById(Long id);

  void update(Student student);
}
