package nextstep.courses.domain.course;

public class CourseInfo {

  private Long id;

  private String title;

  public CourseInfo(Long id, String title) {
    this.id = id;
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
