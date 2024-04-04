package nextstep.courses.domain;

import nextstep.member.Student;

import java.io.File;
import java.util.List;

public class SessionCharged extends Session {
  private Integer maxSize;
  private Integer tuition;

  public SessionCharged(Long id, File image, SessionStatus status, final List<Student> student, final Integer maxSize, final Integer tuition) {
    super(id, image, status, student);
    this.maxSize = maxSize;
    this.tuition = tuition;
  }

  @Override
  public void addStudent(final Student student) {
    if (this.isFull()) {
      throw new IllegalStateException("최대 수강인원을 초과하였습니다.");
    }

    if (this.isNotOpen()) {
      throw new IllegalStateException("강의가 열렸을 경우에만 수강 신청 가능합니다.");
    }

    this.students.add(student);
  }

  private boolean isFull() {
    return this.students.greaterOrEqualTo(maxSize);
  }
}
