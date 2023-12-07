package nextstep.courses.domain;

public interface StudentRepository {
    Long save(Student student);

    Student findById(Long id);
}
