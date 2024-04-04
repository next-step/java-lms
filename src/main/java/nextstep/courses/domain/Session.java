package nextstep.courses.domain;

import nextstep.member.Student;
import nextstep.member.Students;

import java.io.File;
import java.util.List;

public abstract class Session {
  private final Long id;
  private File image;
  private SessionStatus status;
  protected final Students students;

  protected Session(Long id, File image, SessionStatus status, final List<Student> students) {
    this.id = id;
    this.image = image;
    this.status = status;
    this.students = new Students(students);
  }

  protected boolean isNotOpen() {
    return this.status != SessionStatus.OPEN;
  }

  public Integer numberOfStudents() {
    return this.students.size();
  }

  abstract void addStudent(final Student student);
}
