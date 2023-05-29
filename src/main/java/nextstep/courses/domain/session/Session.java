package nextstep.courses.domain.session;

import exception.LmsException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import nextstep.courses.domain.Course;
import nextstep.courses.exception.SessionExceptionCode;
import nextstep.users.domain.NsUser;

public class Session {
  private final Long id;
  private final Long courseId;
  private SessionPayType sessionPayType;
  private SessionStatus sessionStatus;
  private SessionCapacity capacity;
  private final SessionPeriod sessionPeriod;

  private Course course;
  private SessionStudents students = new SessionStudents(new ArrayList<>());

  public Session(
      Long id, Long courseId, SessionPayType sessionPayType, SessionStatus sessionStatus,
      int maxPersonnelCount, LocalDateTime startAt, LocalDateTime finishAt
  ) {
    this.id = id;
    this.sessionPayType = sessionPayType;
    this.sessionStatus = sessionStatus;
    this.capacity = new SessionCapacity(maxPersonnelCount);
    this.courseId = courseId;
    this.sessionPeriod = new SessionPeriod(startAt, finishAt);
  }

  public Session(
      Long id, Course course, SessionPayType payType, SessionStatus sessionStatus,
      int maxPersonnelCount, LocalDateTime startAt, LocalDateTime finishAt
  ) {
    this(id, course.getId(), payType, sessionStatus, maxPersonnelCount, startAt, finishAt);
    this.course = course;
  }

  public Session (Session session, SessionStudents students) {
    this.id = session.id;
    this.sessionPayType = session.sessionPayType;
    this.sessionStatus = session.sessionStatus;
    this.capacity = session.capacity;
    this.courseId = session.courseId;
    this.sessionPeriod = session.sessionPeriod;
    this.students = students;
  }

  public SessionStudent addPersonnel(NsUser nsUser) {
    if (this.sessionStatus != SessionStatus.RECRUITING) {
      throw new LmsException(SessionExceptionCode.ONLY_RECRUITING_STATUS_ALLOWED);
    }

    if (this.cannotAcceptMoreStudent()) {
      throw new LmsException(SessionExceptionCode.EXCEED_MAX_PERSONNEL_COUNT);
    }

    SessionStudent student = new SessionStudent(this, nsUser.getId());
    students.addStudent(student);
    return student;
  }

  public int getMaxCapacity() {
    return this.capacity.getMaxCapacity();
  }

  public Long getId() {
    return id;
  }

  public Long getCourseId() {
    return courseId;
  }

  public SessionPayType getSessionPayType() {
    return sessionPayType;
  }

  public SessionStatus getSessionStatus() {
    return sessionStatus;
  }

  public SessionPeriod getSessionPeriod() {
    return sessionPeriod;
  }

  public SessionStudents getStudents() {
    return students;
  }

  public long getCurrentStudentSize() {
    return students.getCurrentStudentCount();
  }

  public boolean canAcceptMoreStudent() {
    return this.getMaxCapacity() > this.getCurrentStudentSize();
  }

  public boolean cannotAcceptMoreStudent() {
    return !this.canAcceptMoreStudent();
  }
}
