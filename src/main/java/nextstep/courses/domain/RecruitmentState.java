package nextstep.courses.domain;

public enum RecruitmentState {
  PREPARING,
  RECRUITING,
  CLOSED;

  public RecruitmentState next() {
    switch (this) {
      case PREPARING :
        return RECRUITING;
      case RECRUITING :
        return CLOSED;
      case CLOSED :
        throw new IllegalStateException("종료된 강의는 상태를 변경할 수 없습니다.");
    }
    throw new IllegalStateException("잘못된 강의 상태 입니다.");
  }

  public boolean canEnroll() {
    return this == RECRUITING;
  }
}