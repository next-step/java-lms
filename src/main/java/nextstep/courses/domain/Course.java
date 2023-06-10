package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Course {

  private CourseInfo courseInfo;

  private int nowBatchNo;

  private Batches batches = new Batches();

  private BaseInfo baseInfo;

  public Course() {
  }

  public Course(String title, Long creatorId) {
    this(null, title, 0, new Batches(), creatorId, LocalDateTime.now(), null);
  }

  public Course(Long id, String title, Long creatorId, LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this(id, title, 0, new Batches(), creatorId, createdAt, updatedAt);
  }

  public Course(Long id, String title, int nowBatchNo, Batches batches, Long creatorId,
      LocalDateTime createdAt, LocalDateTime updatedAt) {
    this(new CourseInfo(id, title, nowBatchNo),
        batches,
        new BaseInfo(creatorId, createdAt, updatedAt));
  }

  public Course(CourseInfo courseInfo, Batches batches, BaseInfo baseInfo) {
    this.courseInfo = courseInfo;
    this.batches = batches;
    this.baseInfo = baseInfo;
  }

  public Batch createdBatch(Long creatorId) {
    Batch batch = new Batch(++this.nowBatchNo, this, creatorId);
    batches.addBatch(batch);
    return batch;
  }

  public Curriculum addSession(int batchNo, Session session) {
    return batches.addSession(batchNo, session);
  }

  public String getTitle() {
    return courseInfo.getTitle();
  }

  public Long getCreatorId() {
    return baseInfo.getCreatorId();
  }

  public LocalDateTime getCreatedAt() {
    return baseInfo.getCreatedAt();
  }
}
