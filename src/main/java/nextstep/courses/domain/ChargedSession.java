package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.List;

public class ChargedSession extends Session {
  private Integer maxSize;
  private Long tuition;

  public ChargedSession(Long courseId, LocalDate startDate, LocalDate endDate, List<SessionImage> images, OpenStatus openStatus,
                        RecruitStatus recruitStatus, final Integer maxSize, final Long tuition) {
    this(0L, courseId, startDate, endDate, images, openStatus, recruitStatus, maxSize, tuition, List.of());
  }

  public ChargedSession(Long id, Long courseId, LocalDate startDate, LocalDate endDate, List<SessionImage> images, OpenStatus openStatus,
                        RecruitStatus recruitStatus, final Integer maxSize, final Long tuition) {
    this(id, courseId, startDate, endDate, images, openStatus, recruitStatus, maxSize, tuition, List.of());
  }

  public ChargedSession(Long id, Long courseId, LocalDate startDate, LocalDate endDate, List<SessionImage> images, OpenStatus openStatus,
                        RecruitStatus recruitStatus, final Integer maxSize, final Long tuition, final List<NsUser> students) {
    super(id, courseId, startDate, endDate, images, openStatus, recruitStatus, students);
    this.maxSize = maxSize;
    this.tuition = tuition;
  }

  @Override
  protected void validateAddition(final NsUser student) {
    if (this.isFull()) {
      throw new IllegalStateException("최대 수강인원을 초과하였습니다.");
    }

    if (student.notHasPaymentFor(this)) {
      throw new IllegalStateException("일치하는 결제 정보가 없습니다.");
    }
  }

  private boolean isFull() {
    return this.students.greaterOrEqualTo(maxSize);
  }

  public boolean hasTuitionOf(final Long tuition) {
    return this.tuition.equals(tuition);
  }

  public Integer getMaxSize() {
    return this.maxSize;
  }

  public Long getTuition() {
    return this.tuition;
  }

  @Override
  public String getType() {
    return "CHARGED";
  }
}
