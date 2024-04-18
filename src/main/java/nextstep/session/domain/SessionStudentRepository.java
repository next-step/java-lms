package nextstep.session.domain;

import java.util.List;

public interface SessionStudentRepository {

    List<Student> findAllEnrolledInSession(Long sessionId);

    int save(Student student);
}
