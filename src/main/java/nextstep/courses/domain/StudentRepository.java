package nextstep.courses.domain;

import java.util.List;

public interface StudentRepository {
    int save(Student student);

    List<Student> findBySessionId(Long sessionId);

    Student findById(Long studentId);
}
