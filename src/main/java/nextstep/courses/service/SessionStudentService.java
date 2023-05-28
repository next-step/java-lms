package nextstep.courses.service;

import java.util.List;
import java.util.stream.Collectors;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionStudent;
import nextstep.courses.domain.session.SessionStudentRepository;
import nextstep.courses.domain.session.SessionStudents;
import nextstep.users.domain.NsUser;
import nextstep.users.service.NsUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessionStudentService {

  private final SessionStudentRepository sessionStudentRepository;
  private final NsUserService nsUserService;

  public SessionStudentService(SessionStudentRepository jdbcSessionStudentRepository, NsUserService nsUserService) {
    this.sessionStudentRepository = jdbcSessionStudentRepository;
    this.nsUserService = nsUserService;
  }

  @Transactional
  public void enrollStudent (Session session, NsUser nsUser) {
    SessionStudent student = session.addPersonnel(nsUser);
    sessionStudentRepository.takeSession(student.getSessionId(), student.getNsUserId());
  }

  public SessionStudents getStudentsOfSession(Session session) {
    List<SessionStudent> students = sessionStudentRepository.getStudents(session.getId())
        .stream()
        .map(student -> new SessionStudent(student, nsUserService.getUser(student.getNsUserId())))
        .collect(Collectors.toList());

    return new SessionStudents(students);
  }
}
