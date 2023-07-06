package nextstep.courses.domain;

import java.util.List;

public interface StudentRepository {
    int save(Student student);

    Student findById(Long studentId);

    List<Student> findBySessionId(Long sessionId);

    int updateApprovalState(Long sessionId, Long studentId, ApprovalState approvalState);
}
