package nextstep.courses.domain.student;

import java.util.Optional;

public interface StudentRepository {

    int save(Student student);

    Optional<Student> findById(Long id);

}
