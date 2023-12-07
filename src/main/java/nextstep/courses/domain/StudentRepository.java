package nextstep.courses.domain;

public interface StudentRepository {
    int save(Student student);

    Student findById(Long id);
}
