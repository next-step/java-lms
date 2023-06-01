package nextstep.sessions.domain;

import java.util.List;

public interface SessionStudentRepository {
    void takeSession(Long sessionId, Long nsUserId);

    List<SessionStudent> getStudents(Long sessionId);
}
