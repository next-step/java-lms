package nextstep.courses.tobe.domain;

public interface TobeInstructorRepository {
    int save(Instructor instructor);

    Instructor findById(long instructorId);
}
