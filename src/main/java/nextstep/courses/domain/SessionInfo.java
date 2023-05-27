package nextstep.courses.domain;

import java.util.Objects;

public class SessionInfo {

  private final String title;

  private final String description;


  public SessionInfo(String title, String description) {
    this.title = title;
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SessionInfo that = (SessionInfo) o;
    return Objects.equals(title, that.title) && Objects.equals(description,
        that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, description);
  }
}
