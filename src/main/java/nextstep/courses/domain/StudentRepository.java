package nextstep.courses.domain;

import java.util.List;

public interface StudentRepository {

    int save(Student student);

    Student findById(long id);
    List<Student> findBySessionId(long sessionId);
}
