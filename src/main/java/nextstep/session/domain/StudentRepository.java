package nextstep.session.domain;

import nextstep.session.dto.StudentVO;

import java.util.List;

public interface StudentRepository {

    List<Student> findBySessionId(long sessionId);

    long save(Student student);

    int updateDeleteStatus(long sessionId, String studentId, boolean deleteStatus);
}
