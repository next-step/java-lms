package nextstep.courses.domain.session.student;

import java.util.List;

public interface SessionStudentRepository {
  Long takeSession(Long sessionId, Long nsUserId);
  int cancelSession(Long sessionId, Long nsUserId);
  List<SessionStudent> getStudents(Long sessionId);
}
