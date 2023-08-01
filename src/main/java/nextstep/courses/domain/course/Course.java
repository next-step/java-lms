package nextstep.courses.domain.course;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.courses.domain.base.BaseInfo;

public class Course {

  private final CourseInfo courseInfo;

  private final BaseInfo baseInfo;

  public Course(String title, Long creatorId) {
    this(null, title, creatorId);
  }

  public Course(Long id, String title, Long creatorId) {
    this(id, title, creatorId, LocalDateTime.now(), LocalDateTime.now());
  }

  public Course(Long id, String title, Long creatorId, LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this(new CourseInfo(id, title)
        , new BaseInfo(creatorId, createdAt, updatedAt));
  }

  public Course(CourseInfo courseInfo, BaseInfo baseInfo) {
    this.courseInfo = courseInfo;
    this.baseInfo = baseInfo;
  }

  public void changeTitle(String title) {
    courseInfo.changeTitle(title);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Course course = (Course) o;
    return Objects.equals(courseInfo, course.courseInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseInfo);
  }

  public Long getId() {
    return courseInfo.getId();
  }

  public String getTitle() {
    return courseInfo.getTitle();
  }

  public Long getCreatorId() {
    return baseInfo.getCreatorId();
  }
}
