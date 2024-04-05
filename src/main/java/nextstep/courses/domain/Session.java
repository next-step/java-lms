package nextstep.courses.domain;

import nextstep.member.Student;
import nextstep.member.Students;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public abstract class Session {
  private final Long id;
  private final LocalDate startDate;
  private final LocalDate endDate;
  private File image;
  private SessionStatus status;
  protected final Students students;

  protected Session(Long id, LocalDate startDate, LocalDate endDate, File image, SessionStatus status, final List<Student> students) {
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
    this.image = image;
    this.status = status;
    this.students = new Students(students);
    validate();
  }

  private void validate() {
    if (datesInvalid()) {
      throw new IllegalArgumentException("강의 시작일이 종료일 이후일 수 없습니다.");
    }
  }

  private boolean datesInvalid() {
    return this.startDate.isAfter(this.endDate);
  }

  protected boolean isNotOpen() {
    return this.status != SessionStatus.OPEN;
  }

  public Integer numberOfStudents() {
    return this.students.size();
  }

  public boolean isIdOf(final Long id) {
    return this.id.equals(id);
  }

  public abstract void addStudent(final Student student);
}
