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
  private List<SessionImage> images;
  private SessionStatus status;
  private OpenStatus openStatus;
  private RecruitStatus recruitStatus;
  protected final Users students = new Users();

  protected Session(Long courseId, LocalDate startDate, LocalDate endDate, List<SessionImage> images, OpenStatus openStatus,
                    RecruitStatus recruitStatus) {
    this(0L, courseId, startDate, endDate, images, openStatus, recruitStatus, List.of());
  }

  protected Session(Long id, Long courseId, LocalDate startDate, LocalDate endDate, List<SessionImage> images, OpenStatus openStatus,
                    RecruitStatus recruitStatus) {
    this(id, courseId, startDate, endDate, images, openStatus, recruitStatus, List.of());
  }

  protected Session(Long id, Long courseId, LocalDate startDate, LocalDate endDate, List<SessionImage> images,
                    OpenStatus openStatus, RecruitStatus recruitStatus, final List<NsUser> students) {
    this.id = id;
    this.courseId = courseId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.images = images;
    this.openStatus = openStatus;
    this.recruitStatus = recruitStatus;
    this.students.addAll(students);
    validate();
  }

  private void validate() {
    if (datesInvalid()) {
      throw new IllegalArgumentException("강의 시작일이 종료일 이후일 수 없습니다.");
    }
  }

  public Registration addStudent(final NsUser student) {
    if (this.isNotRecruiting()) {
      throw new IllegalStateException("수강생 모집중인 강의가 아닙니다.");
    }

    validateAddition(student);

    this.students.add(student);

    return new Registration(this, student);
  }

  protected abstract void validateAddition(final NsUser user);

  public abstract String getType();

  private boolean datesInvalid() {
    return this.startDate.isAfter(this.endDate);
  }

  protected boolean isNotRecruiting() {
    return this.recruitStatus == RecruitStatus.CLOSED;
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

  public Long getId() {
    return this.id;
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

  public RecruitStatus getStatus() {
    return this.recruitStatus;
  }

  public RecruitStatus getRecruitStatus() {
    return this.recruitStatus;
  }

  public OpenStatus getOpenStatus() {
    return this.openStatus;
  }

  public List<SessionImage> images() {
    return this.images;
  }

  @Override
  public String toString() {
    return "Session{" +
            "id=" + id +
            '}';
  }
}
