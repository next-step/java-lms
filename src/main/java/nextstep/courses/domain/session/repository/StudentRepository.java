package nextstep.courses.domain.session.repository;

import nextstep.courses.domain.session.student.Student;
import nextstep.courses.domain.session.student.Students;

import java.util.Optional;

public interface StudentRepository {

    void save(Student student);
    Students findAllByEnrolment(Long id);
    Optional<Student> findById(Long studentId);
}
