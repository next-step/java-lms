package nextstep.session.domain;

import java.util.List;

public interface SessionStudentRepository {

    List<Student> findAllEnrolledInSession(Long sessionId);

    List<Student> findAllApprovedStudents(Long sessionId);

    int save(Student student);

    int updateStatus(Student student);

}
