package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.Users;

import java.time.LocalDate;
import java.util.List;

public abstract class Session {
  private Long id;
  private Long courseId;
  private LocalDate startDate;
  private LocalDate endDate;
  private SessionImage image;
  private SessionStatus status;
  protected final Users students = new Users();

  protected Session(Long id, Long courseId, LocalDate startDate, LocalDate endDate, SessionImage image, SessionStatus status, final List<NsUser> students) {
    this.id = id;
    this.courseId = courseId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.image = image;
    this.status = status;
    this.students.addAll(students);
    validate();
  }

  protected Session(Long courseId, LocalDate startDate, LocalDate endDate, SessionImage image, SessionStatus status) {
    this(0L, courseId, startDate, endDate, image, status, List.of());
  }

  protected Session(Long id, Long courseId, LocalDate startDate, LocalDate endDate, SessionImage image, SessionStatus status) {
    this(id, courseId, startDate, endDate, image, status, List.of());
    validate();
  }

  private void validate() {
    if (datesInvalid()) {
      throw new IllegalArgumentException("강의 시작일이 종료일 이후일 수 없습니다.");
    }
  }

  public Registration addStudent(final NsUser student) {
    if (this.isNotOpen()) {
      throw new IllegalStateException("수강생 모집중인 강의가 아닙니다.");
    }

    validateAddition(student);

    this.students.add(student);

    return new Registration(this.id, student.getId());
  }

  protected abstract void validateAddition(final NsUser user);

  public abstract String getType();

  private boolean datesInvalid() {
    return this.startDate.isAfter(this.endDate);
  }

  protected boolean isNotOpen() {
    return this.status != SessionStatus.OPEN;
  }

  public Integer numberOfStudents() {
    return this.students.size();
  }

  public boolean hasIdOf(final Long id) {
    return this.id.equals(id);
  }

  public boolean hasStudent(final NsUser student) {
    return this.students.contains(student);
  }

  public Long getCourseId() {
    return this.courseId;
  }

  public LocalDate getStartDate() {
    return this.startDate;
  }

  public LocalDate getEndDate() {
    return this.endDate;
  }

  public Long getSessionImageId() {
    return this.image.getId();
  }

  public SessionStatus getStatus() {
    return this.status;
  }

  public SessionImage image() {
    return this.image;
  }
}
