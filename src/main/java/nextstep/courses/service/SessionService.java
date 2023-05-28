package nextstep.courses.service;

import exception.LmsException;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.SessionStudents;
import nextstep.courses.exception.SessionExceptionCode;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

  private final SessionRepository sessionRepository;
  private final SessionStudentService sessionStudentService;

  public SessionService(SessionRepository jdbcSessionRepository, SessionStudentService sessionStudentService) {
    this.sessionRepository = jdbcSessionRepository;
    this.sessionStudentService = sessionStudentService;
  }

  public void takeSession (NsUser nsUser, Long sessionId) {
    Session session = this.getSessionWithStudents(sessionId);
    if (session.getSessionStatus() != SessionStatus.RECRUITING) {
      throw new LmsException(SessionExceptionCode.ONLY_RECRUITING_STATUS_ALLOWED);
    }

    sessionStudentService.enrollStudent(session, nsUser);
  }

  public Session getSessionWithStudents(Long sessionId) {
    Session session = sessionRepository.findById(sessionId)
        .orElseThrow(() -> new LmsException(SessionExceptionCode.SESSION_NOT_FOUND));

    SessionStudents studentsOfSession = sessionStudentService.getStudentsOfSession(session);

    return new Session(session, studentsOfSession);
  }
}
