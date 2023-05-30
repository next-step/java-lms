package nextstep.courses.service;

import exception.LmsException;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.domain.session.student.SessionStudents;
import nextstep.courses.domain.session.teacher.SessionTeacher;
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
  public void takeSession (NsUser studentUser, Long sessionId) {
    Session session = this.getSessionWithStudents(sessionId);
    sessionStudentService.enrollStudent(session, studentUser);
  }

  @Transactional
  public void cancelSession(NsUser studentUser, Long sessionId) {
    Session session = this.getSessionWithStudents(sessionId);
    SessionStudent student = session.cancelStudent(studentUser.getId());
    sessionStudentService.cancelSession(student);
  }

  @Transactional
  public int approveSession (NsUser studentUser, Long sessionId, NsUser teacherUser) {
    Session session = this.getSessionWithStudents(sessionId);
    if (session.isLegacy()) {
      return 0;
    }

    SessionTeacher teacher = session.getTeacher(teacherUser.getId());
    SessionStudent student = session.getStudent(studentUser.getId());

    return sessionStudentService.approveSession(session, teacher, student);
  }

  @Transactional
  public int refuseSession (NsUser studentUser, Long sessionId, NsUser teacherUser) {
    Session session = this.getSessionWithStudents(sessionId);
    if (session.isLegacy()) {
      return 0;
    }

    SessionTeacher teacher = session.getTeacher(teacherUser.getId());
    SessionStudent student = session.getStudent(studentUser.getId());

    return sessionStudentService.refuseSession(session, teacher, student);
  }

  public Session getSessionWithStudents(Long sessionId) {
    Session session = sessionRepository.findById(sessionId)
        .orElseThrow(() -> new LmsException(SessionExceptionCode.SESSION_NOT_FOUND));

    SessionStudents studentsOfSession = sessionStudentService.getStudentsOfSession(session);
    SessionTeachers teachersOfSession = sessionTeacherService.getTeachersOfSession(session);
    return new Session(session, studentsOfSession, teachersOfSession);
  }
}
