package nextstep.courses.domain;

public enum SessionType {

  FREE("무료"),
  PAID("유료");

  private final String description;

  SessionType(String description) {
    this.description = description;
  }

}
