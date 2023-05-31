package nextstep.lms.domain;

import java.util.Optional;

public interface StudentRepository {
    int save(Student student);

    Optional<Student> findByNsUserIdAndSessionId(Long nsUserId, Long sessionId);

    void sessionCancel(Student student);

    void changeStudentSelectedType(Student student);

    void changeStudentApprovedType(Student student);
}
