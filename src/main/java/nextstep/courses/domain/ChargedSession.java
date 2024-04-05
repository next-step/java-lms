package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class ChargedSession extends Session {
  private Integer maxSize;
  private Long tuition;

  public ChargedSession(Long id, File image, SessionStatus status, final List<NsUser> students,
                        LocalDate startDate, LocalDate endDate, final Integer maxSize, final Long tuition) {
    super(id, startDate, endDate, image, status, students);
    this.maxSize = maxSize;
    this.tuition = tuition;
  }

  public ChargedSession(Long id, File image, SessionStatus status, LocalDate startDate, LocalDate endDate,
                        final Integer maxSize, final Long tuition) {
    super(id, startDate, endDate, image, status);
    this.maxSize = maxSize;
    this.tuition = tuition;
  }

  @Override
  public void addStudent(final NsUser student) {
    if (this.isNotOpen()) {
      throw new IllegalStateException("수강생 모집중인 강의가 아닙니다.");
    }

    if (this.isFull()) {
      throw new IllegalStateException("최대 수강인원을 초과하였습니다.");
    }

    if (student.notHasPaymentFor(this)) {
      throw new IllegalStateException("일치하는 결제 정보가 없습니다.");
    }

    this.students.add(student);
  }

  public boolean isTuitionOf(final Long tuition) {
    return this.tuition.equals(tuition);
  }

  private boolean isFull() {
    return this.students.greaterOrEqualTo(maxSize);
  }
}
