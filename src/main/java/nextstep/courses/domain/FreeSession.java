package nextstep.courses.domain;

import nextstep.member.Student;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class FreeSession extends Session {

  protected FreeSession(Long id, LocalDate startDate, LocalDate endDate, File image, SessionStatus status, List<Student> students) {
    super(id, startDate, endDate, image, status, students);
  }

  @Override
  public void addStudent(Student student) {
    if (this.isNotOpen()) {
      throw new IllegalStateException("수강생 모집중인 강의가 아닙니다.");
    }

    this.students.add(student);
  }
}
