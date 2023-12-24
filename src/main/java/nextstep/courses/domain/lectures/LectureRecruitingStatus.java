package nextstep.courses.domain.lectures;

public enum LectureRecruitingStatus {
  RECRUITING("RECRUITING"),
  CLOSING("CLOSING"),
  PREPARING("PREPARING"),
  NO_MATCH("NO_MATCH")
  ;
  private final String name;

  LectureRecruitingStatus(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
