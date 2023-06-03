package nextstep.users.domain;

public interface InstructorRepository {
    int save(Instructor student);

    Instructor findById(long id);
}
