package nextstep.session.domain;

import nextstep.session.dto.StudentVO;

import java.util.List;

public interface StudentRepository {

    List<StudentVO> findBySessionId(long sessionId);

    long save(StudentVO studentVO);

    int updateDeleteStatus(long sessionId, String studentId, boolean deleteStatus);
}
