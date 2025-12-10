package nextstep.courses.domain.session.recruit;

public class Recruiting implements RecruitmentStatus{

  @Override
  public boolean canEnroll() {
    return true;
  }

  @Override
  public RecruitmentStatus open() {
    throw new IllegalStateException("이미 모집중입니다.");
  }

  @Override
  public RecruitmentStatus close() {
    return new NotRecruiting();
  }
}
