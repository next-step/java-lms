package nextstep.courses.infrastructure;

import nextstep.courses.domain.Student;

import java.util.List;

public interface StudentRepository {
    int save(Student student);

    Student findById(Long studentId);

    List<Student> findBySessionId(Long sessionId);
}
