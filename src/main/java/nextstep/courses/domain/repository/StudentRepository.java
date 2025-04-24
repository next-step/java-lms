package nextstep.courses.domain.repository;

import nextstep.courses.domain.model.Student;

public interface StudentRepository {
    long save(Student course);

    Student findById(Long id);
}
