package nextstep.courses.service;

import java.util.List;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.teacher.SessionTeacher;
import nextstep.courses.domain.session.teacher.SessionTeacherRepository;
import nextstep.courses.domain.session.teacher.SessionTeachers;
import org.springframework.stereotype.Service;

@Service
public class SessionTeacherService {

  private final SessionTeacherRepository sessionTeacherRepository;

  public SessionTeacherService(SessionTeacherRepository jdbcSessionTeacherRepository) {
    this.sessionTeacherRepository = jdbcSessionTeacherRepository;
  }

  public SessionTeachers getTeachersOfSession(Session session) {
    List<SessionTeacher> students = sessionTeacherRepository.getTeachers(session.getId());
    return new SessionTeachers(students);
  }
}
