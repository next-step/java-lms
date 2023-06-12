package nextstep.courses.domain;

import nextstep.courses.domain.enrollment.Student;

import java.util.List;

public interface StudentRepository {

    int save(Student student);

    Student findById(Long studentId);

    List<Student> findAllBySessionId(Long sessionId);
}
