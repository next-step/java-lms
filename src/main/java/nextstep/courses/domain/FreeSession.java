package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class FreeSession extends Session {

  public FreeSession(Long id, LocalDate startDate, LocalDate endDate, File image, SessionStatus status) {
    super(id, startDate, endDate, image, status, List.of());
  }

  public FreeSession(Long id, LocalDate startDate, LocalDate endDate, File image, SessionStatus status, List<NsUser> students) {
    super(id, startDate, endDate, image, status, students);
  }

  @Override
  public void addStudent(NsUser student) {
    if (this.isNotOpen()) {
      throw new IllegalStateException("수강생 모집중인 강의가 아닙니다.");
    }

    this.students.add(student);
  }
}
