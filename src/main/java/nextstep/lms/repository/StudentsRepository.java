package nextstep.lms.repository;

import nextstep.lms.domain.Student;
import nextstep.lms.domain.Students;

public interface StudentsRepository {
    int save(Student student);

    Students findBySession(Long sessionId);

    int updateStatus(Student student);
}
