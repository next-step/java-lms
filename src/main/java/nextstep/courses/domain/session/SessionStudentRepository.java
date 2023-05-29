package nextstep.courses.domain.session;

import java.util.List;
import nextstep.users.domain.NsUser;

public interface SessionStudentRepository {
  Long takeSession(Long sessionId, Long nsUserId);
  int cancelSession(Long sessionId, Long nsUserId);
  List<SessionStudent> getStudents(Long sessionId);
}
