package nextstep.courses.domain;

public interface InstructorRepository {
    int save(Instructor instructor);

    Instructor findById(long instructorId);
}
