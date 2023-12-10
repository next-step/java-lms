package nextstep.courses.domain.lectures;

public enum LectureStatus {
  RECRUITING("RECRUITING")
  , CLOSING("CLOSING")
  , PREPARING("PREPARING")
  , NO_MATCH("NO_MATCH")
  ;
  private final String name;

  LectureStatus(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
