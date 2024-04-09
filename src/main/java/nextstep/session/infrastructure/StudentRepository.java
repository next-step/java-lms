package nextstep.session.infrastructure;

import nextstep.session.dto.StudentDto;

import java.util.List;

public interface StudentRepository {

    List<StudentDto> findBySessionId(long sessionId);

    long save(StudentDto studentDto);

    int updateDeleteStatus(long sessionId, String studentId, boolean deleteStatus);
}
