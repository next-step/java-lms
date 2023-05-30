package nextstep.courses.service;

import exception.LmsException;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.student.SessionStudents;
import nextstep.courses.domain.session.teacher.SessionTeachers;
import nextstep.courses.exception.SessionExceptionCode;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessionService {

  private final SessionRepository sessionRepository;
  private final SessionStudentService sessionStudentService;
  private final SessionTeacherService sessionTeacherService;

  public SessionService(
      SessionRepository jdbcSessionRepository, SessionStudentService sessionStudentService, SessionTeacherService sessionTeacherService
  ) {
    this.sessionRepository = jdbcSessionRepository;
    this.sessionStudentService = sessionStudentService;
    this.sessionTeacherService = sessionTeacherService;
  }

  @Transactional
  public void takeSession (NsUser nsUser, Long sessionId) {
    Session session = this.getSessionWithStudents(sessionId);
    sessionStudentService.enrollStudent(session, nsUser);
  }

  public Session getSessionWithStudents(Long sessionId) {
    Session session = sessionRepository.findById(sessionId)
        .orElseThrow(() -> new LmsException(SessionExceptionCode.SESSION_NOT_FOUND));

    SessionStudents studentsOfSession = sessionStudentService.getStudentsOfSession(session);
    SessionTeachers teachersOfSession = sessionTeacherService.getTeachersOfSession(session);
    return new Session(session, studentsOfSession, teachersOfSession);
  }
}
