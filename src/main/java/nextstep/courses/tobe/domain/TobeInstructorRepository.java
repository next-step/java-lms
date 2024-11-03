package nextstep.courses.tobe.domain;

import nextstep.courses.domain.Instructor;

public interface TobeInstructorRepository {
    int save(Instructor instructor);

    Instructor findById(long instructorId);
}
