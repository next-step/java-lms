package nextstep.courses.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import nextstep.courses.domain.BaseTimeEntity;
import nextstep.courses.domain.Course;

public class CourseEntity extends BaseTimeEntity {

  private Long id;

  private String title;

  private Long creatorId;

  private String generation;

  public CourseEntity() {
  }

  /**
   * 주 생성자
   */
  public CourseEntity(Long id, String title, Long creatorId, String generation,
      LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(createdAt, updatedAt);
    this.id = id;
    this.title = title;
    this.creatorId = creatorId;
    this.generation = generation;
  }

  public CourseEntity(String title, Long creatorId, String generation) {
    this(null, title, creatorId, generation, LocalDateTime.now(), LocalDateTime.now());
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public Long getCreatorId() {
    return creatorId;
  }

  public String getGeneration() {
    return generation;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public Course toDomain() {
    return new Course(id, title, creatorId, generation, createdAt, updatedAt);
  }

}
