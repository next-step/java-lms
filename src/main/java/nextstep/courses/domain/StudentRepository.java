package nextstep.courses.domain;

import nextstep.courses.domain.registration.Student;

import java.util.List;

public interface StudentRepository {

    int save(Student student);

    Student findById(Long studentId);

    List<Student> findAllBySessionId(Long sessionId);
}
