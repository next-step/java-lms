package nextstep.courses.domain.session.student;

import java.util.List;

public interface SessionStudentRepository {
  Long takeSession(Long sessionId, Long nsUserId);
  int cancelSession(Long studentId);
  List<SessionStudent> getStudents(Long sessionId);
  int changeStudentStatus(Long id, Long teacherNsUserId, Long studentId, SessionStudentStatus studentStatus);
}
