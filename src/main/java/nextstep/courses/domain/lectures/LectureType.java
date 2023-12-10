package nextstep.courses.domain.lectures;

public enum LectureType {
  FREE("FREE")
  , PAID("PAID")
  ;
  private final String name;

  LectureType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
