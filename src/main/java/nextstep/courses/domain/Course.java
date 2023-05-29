package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Course {

  private Long id;

  private String title;

  private int nowBatchNo = 0;

  private Batches batches = new Batches();

  private Long creatorId;

  private LocalDateTime createdAt = LocalDateTime.now();

  private LocalDateTime updatedAt;

  public Course() {
  }

  public Course(String title, Long creatorId) {
    this(null, title, creatorId, LocalDateTime.now(), null);
  }

  public Course(Long id, String title, Long creatorId, LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this.id = id;
    this.title = title;
    this.creatorId = creatorId;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Batch createdBatch(Long creatorId) {
    Batch batch = new Batch(++this.nowBatchNo, this, creatorId);
    batches.addBatch(batch);
    return batch;
  }

  public void addSession(int batchNo, Session session) {
    batches.addSession(batchNo, session);
  }

  public String getTitle() {
    return title;
  }

  public Long getCreatorId() {
    return creatorId;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  @Override
  public String toString() {
    return "Course{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", creatorId=" + creatorId +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        '}';
  }
}
