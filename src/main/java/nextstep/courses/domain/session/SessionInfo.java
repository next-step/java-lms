package nextstep.courses.domain.session;

import java.util.Objects;

public class SessionInfo {

  private final Long id;

  private final String title;

  private final String img;

  public SessionInfo(Long id, String title, String img) {
    this.id = id;
    this.title = title;
    this.img = img;
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
    return Objects.equals(id, that.id) && Objects.equals(title, that.title)
        && Objects.equals(img, that.img);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, img);
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getImg() {
    return img;
  }


}
