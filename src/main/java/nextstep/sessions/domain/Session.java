package nextstep.sessions.domain;

import java.time.LocalDateTime;
import java.util.Set;
import nextstep.sessions.domain.students.SessionRegistration;
import nextstep.sessions.domain.students.Student;
import nextstep.sessions.domain.students.Students;

/**
 * 객체를 생성한 후에 validate 메서드를 통해 명시적으로 검증해야 한다
 *
 * @date 2023-06-03
 * @author jerryk026
 */
public class Session {

  private Long id;

  private SessionDate sessionDate;

  private SessionBody sessionBody;

  private SessionRegistration sessionRegistration;

  public Session(String title, String contents, LocalDateTime startDateTime, LocalDateTime endDateTime, byte[] coverImage, int capacity) {
    this(0L, new SessionDate(startDateTime, endDateTime), new SessionBody(title, contents, coverImage), new SessionRegistration(capacity));
  }

  public Session(SessionDate sessionDate, SessionBody sessionBody, SessionRegistration sessionRegistration) {
    this(0L, sessionDate, sessionBody, sessionRegistration);
  }

  public Session(Long id, SessionDate sessionDate, SessionBody sessionBody, SessionRegistration sessionRegistration) {
    this.id = id;
    this.sessionDate = sessionDate;
    this.sessionBody = sessionBody;
    this.sessionRegistration = sessionRegistration;
  }

  public void recruitStart() {
    this.sessionRegistration.recruitStart();
  }

  public void recruitEnd() {
    this.sessionRegistration.recruitEnd();
  }

  public void enrollment(Students students, Student student) {
    sessionDate.validateEnrolment(student.getCreatedAt());
    sessionRegistration.enrolment(students, student);
  }

  public void validateInit() {
    sessionDate.validateInit();
    sessionRegistration.validateInit();
  }

  public void accept(Student student) {
    sessionRegistration.accept(student);
  }

  public Long getId() {
    return this.id;
  }

  public LocalDateTime getStartDate() {
    return this.sessionDate.getStartDateTime();
  }

  public LocalDateTime getEndDate() {
    return this.sessionDate.getEndDateTime();
  }

  public String getTitle() {
    return this.sessionBody.getTitle();
  }

  public String getContents() {
    return this.sessionBody.getContents();
  }

  public byte[] getCoverImage() {
    return this.sessionBody.getCoverImage();
  }

  public int getCapacity() {
    return this.sessionRegistration.getCapacity();
  }

  public Students getStudents() {
    return this.sessionRegistration.getStudents();
  }

  public Set<Student> studentsAsSet() {
    return this.sessionRegistration.asSet();
  }

  public SessionRecruitingStatus getRecruitingStatus() {
    return this.sessionRegistration.getRecruitingStatus();
  }

  public SessionProgressStatus getProgressStatus() {
    return this.sessionRegistration.getProgressStatus();
  }

  public SessionRegistration getSessionRegistration() {
    return this.sessionRegistration;
  }

  @Override
  public String toString() {
    return "Session{" +
        "id=" + id +
        ", sessionDate=" + sessionDate +
        ", sessionBody=" + sessionBody +
        ", sessionRegistration=" + sessionRegistration +
        '}';
  }
}
