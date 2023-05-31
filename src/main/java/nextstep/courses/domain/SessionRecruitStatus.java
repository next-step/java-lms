package nextstep.courses.domain;

public enum SessionRecruitStatus {

  RECRUITING("모집중"),
  NOT_RECRUITING("비모집중");

  private final String description;

  SessionRecruitStatus(String description) {
    this.description = description;
  }

  public boolean isRecruiting() {
    return this == RECRUITING;
  }

  public boolean isNotRecruiting() {
    return this == NOT_RECRUITING;
  }


  public void validateEnrollAvailable() {
    if (isNotRecruiting()) {
      throw new IllegalArgumentException("비모집중인 세션은 수강 신청할 수 없습니다.");
    }
  }
}
