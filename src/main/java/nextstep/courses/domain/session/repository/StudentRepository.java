package nextstep.courses.domain.session.repository;

import nextstep.courses.domain.session.student.Student;
import nextstep.courses.domain.session.student.Students;

public interface StudentRepository {

    void save(Student student);

    Students findAllBySession(Long id);
}
