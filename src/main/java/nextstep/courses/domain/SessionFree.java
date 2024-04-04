package nextstep.courses.domain;

import nextstep.member.Student;

import java.io.File;
import java.util.List;

public class SessionFree extends Session {

  protected SessionFree(Long id, File image, SessionStatus status, List<Student> students) {
    super(id, image, status, students);
  }

  @Override
  void addStudent(Student student) {
    this.students.add(student);
  }
}
