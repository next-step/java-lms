package nextstep.courses.service;

import java.util.List;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.domain.session.student.SessionStudentRepository;
import nextstep.courses.domain.session.student.SessionStudents;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessionStudentService {

  private final SessionStudentRepository sessionStudentRepository;

  public SessionStudentService(SessionStudentRepository jdbcSessionStudentRepository) {
    this.sessionStudentRepository = jdbcSessionStudentRepository;
  }

  @Transactional
  public void enrollStudent (Session session, NsUser nsUser) {
    SessionStudent student = session.addPersonnel(nsUser);
    sessionStudentRepository.takeSession(student.getSessionId(), student.getNsUserId());
  }

  public SessionStudents getStudentsOfSession(Session session) {
    List<SessionStudent> students = sessionStudentRepository.getStudents(session.getId());
    return new SessionStudents(students);
  }
}
