package nextstep.courses.domain;

import nextstep.courses.domain.enrollment.Student;

import java.util.List;

public interface StudentRepository {

    int save(Student student);

    List<Student> findBySessionId(Long sessionId);
}
