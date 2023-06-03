package nextstep.lms.domain;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    int save(Student student);

    List<Student> findBySelectedTypeAndSessionId(StudentSelectedType selectedType, Long sessionId);

    Optional<Student> findByNsUserIdAndSessionId(Long nsUserId, Long sessionId);

    void sessionCancel(Student student);

    void updateCanceledStudents(List<Student> students);

    void changeStudentSelectedType(Student student);

    void changeStudentApprovedType(Student student);
}
