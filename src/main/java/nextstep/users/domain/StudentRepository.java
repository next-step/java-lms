package nextstep.users.domain;

public interface StudentRepository {
    int save(Student student);

    Student findById(long id);
}
