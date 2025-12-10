package nextstep.courses.domain.session.recruit;

public class NotRecruiting implements RecruitmentStatus{

  @Override
  public boolean canEnroll() {
    return false;
  }

  @Override
  public RecruitmentStatus open() {
    return new Recruiting();
  }

  @Override
  public RecruitmentStatus close() {
    throw new IllegalStateException("모집중이 아닙니다.");
  }
}
