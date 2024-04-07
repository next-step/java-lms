package nextstep.courses.domain.course;

import nextstep.courses.domain.BaseTime;
import nextstep.courses.domain.session.Session;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Course extends BaseTime {
  private Long id;

  private CourseDetailInfo courseDetailInfo;

  private Generation generation;

  private Set<Session> sessions = new HashSet<>();

  private Course() {}

  public Course(String title, Long creatorId) {
    this(0L, title, creatorId, LocalDateTime.now(), null);
  }

  public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.generation = new Generation(1L);
    this.courseDetailInfo = new CourseDetailInfo(creatorId, title);
    this.createdAt = createdAt;
    super.updatedAt = updatedAt;
  }

  public Course(Long id, Generation generation, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.generation = generation;
    this.courseDetailInfo = new CourseDetailInfo(creatorId, title);
    this.createdAt = createdAt;
    super.updatedAt = updatedAt;
  }

  public void addSessions(Set<Session> sessions) {
    this.sessions.addAll(sessions);
  }

  public String getTitle() {
    return courseDetailInfo.getTitleName();
  }

  public Long getCreatorId() {
    return courseDetailInfo.getCreatorId();
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  @Override
  public String toString() {
    return "Course{" +
        "id=" + id +
        ", title='" + courseDetailInfo.getTitleName() + '\'' +
        ", creatorId=" + courseDetailInfo.getCreatorId() +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        '}';
  }
}
