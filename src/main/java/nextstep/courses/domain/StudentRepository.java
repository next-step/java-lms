package nextstep.courses.domain;

import java.util.Optional;

public interface StudentRepository {
    int save(Student student);

    Students findBySessionId(long sessionId);

    Optional<Student> findByStudentIdAndSessionId(long studentId,
                                                  long sessionId);

    void update(Student student);
}
