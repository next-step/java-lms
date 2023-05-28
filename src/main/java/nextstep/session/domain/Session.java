package nextstep.session.domain;

import java.time.LocalDateTime;
import nextstep.session.CannotApplySession;

public class Session {

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private String imageUrl;

  private boolean isBilling;

  private Status status;

  private int maxEnrollment;

  private int enrollment;

  public Session(Status status, int maxEnrollment, int enrollment) {
    this.status = status;
    this.maxEnrollment = maxEnrollment;
    this.enrollment = enrollment;
  }

  void apply() {
    if (isMaxEnrollment()) {
      throw new CannotApplySession("수강 가능한 인원이 다 찼습니다.");
    }

    if (!isRecruiting()) {
      throw new CannotApplySession("강의 모집 중에만 수강 신청이 가능합니다.");
    }

    enrollment();
  }

  private boolean isRecruiting() {
    return status == Status.RECRUITING;
  }

  private void enrollment() {
    enrollment++;
  }

  private boolean isMaxEnrollment() {
    return enrollment >= maxEnrollment;
  }

}
