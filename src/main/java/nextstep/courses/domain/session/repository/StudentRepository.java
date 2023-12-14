package nextstep.courses.domain.session.repository;

import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.domain.session.student.SessionStudents;

import java.util.Optional;

public interface StudentRepository {

    void save(SessionStudent student);
    void update(SessionStudent student);
    SessionStudents findAllByEnrolment(Long id);
    Optional<SessionStudent> findById(Long studentId);
}
