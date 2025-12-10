package nextstep.courses.domain.session.recruit;

public interface RecruitmentStatus {
  boolean canEnroll();
  RecruitmentStatus open();
  RecruitmentStatus close();
}
