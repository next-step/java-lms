package nextstep.session.domain.student;

import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.Optional;

public interface SessionStudentRepository {
    Long enrollStudent(Long sessionId, Long nsUserId);

    Optional<SessionStudent> getStudentById(Long studentId);

    List<SessionStudent> getStudents(Long sessionId);

    Optional<SessionStudent> getStudent(Long sessionId, Long nsUserId);

    List<NsUser> findAllUserBySessionId(Long sessionId);

    int changeStudentStatus(Long studentId, SessionStudentStatus status);

}
