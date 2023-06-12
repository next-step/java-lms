package nextstep.courses.repository;

import nextstep.courses.domain.session.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> findBySessionId(Long sessionId);

    int registerSession(Long sessionId, Long userId);

    int approve(Student student);

    int reject(Student student);
}
