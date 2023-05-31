package nextstep.courses.domain.session.teacher;

import exception.LmsException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import nextstep.courses.exception.SessionExceptionCode;
import nextstep.users.domain.NsUser;
import org.springframework.util.CollectionUtils;

public class SessionTeachers {
  private final Map<Long, SessionTeacher> teacherMap;

  public SessionTeachers(List<SessionTeacher> teachers) {
    this.teacherMap = getAsMap(teachers);
  }

  public boolean contains(NsUser nsUser) {
    SessionTeacher sessionTeacher = teacherMap.get(nsUser.getId());
    if(sessionTeacher == null) {
      return false;
    }

    return sessionTeacher.isActive();
  }

  public SessionTeacher getTeacher(Long nsUserId) {
    SessionTeacher teacher = teacherMap.get(nsUserId);
    if (teacher == null) {
      throw new LmsException(SessionExceptionCode.TEACHER_NOT_FOUND);
    }
    return teacher;
  }

  private Map<Long, SessionTeacher> getAsMap(List<SessionTeacher> teachers) {
    if (CollectionUtils.isEmpty(teachers)) {
      return new HashMap<>();
    }

    return teachers.stream()
        .collect(Collectors.toMap(SessionTeacher::getNsUserId, Function.identity()));
  }
}
