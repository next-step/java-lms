package nextstep.courses.domain.application;

import java.util.Objects;

public class ApplicationInfo {

  private final Long id;

  private boolean pass;

  public ApplicationInfo(Long id, boolean pass) {
    this.id = id;
    this.pass = pass;
  }

  public void applicationPass() {
    pass = true;
  }

  public void applicationFail() {
    pass = false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApplicationInfo that = (ApplicationInfo) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public Long getId() {
    return id;
  }

  public boolean isPass() {
    return pass;
  }
}
