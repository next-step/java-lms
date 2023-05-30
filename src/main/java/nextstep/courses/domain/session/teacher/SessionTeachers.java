package nextstep.courses.domain.session.teacher;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import nextstep.users.domain.NsUser;

public class SessionTeachers {
  private final List<SessionTeacher> teachers;
  private final Map<Long, SessionTeacher> teacherMap;

  public SessionTeachers(List<SessionTeacher> teachers) {
    this.teachers = teachers;
    this.teacherMap = teachers.stream()
        .collect(Collectors.toMap(SessionTeacher::getNsUserId, Function.identity()));
  }

  public boolean contains(NsUser nsUser) {
    SessionTeacher sessionTeacher = teacherMap.get(nsUser.getId());
    if(sessionTeacher == null) {
      return false;
    }

    return sessionTeacher.isActive();
  }
}
