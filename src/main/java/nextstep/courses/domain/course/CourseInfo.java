package nextstep.courses.domain.course;

import java.util.Objects;

public class CourseInfo {

  private Long id;

  private String title;

  public CourseInfo(Long id, String title) {
    this.id = id;
    this.title = title;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseInfo that = (CourseInfo) o;
    return Objects.equals(id, that.id) && Objects.equals(title, that.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title);
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }
}
