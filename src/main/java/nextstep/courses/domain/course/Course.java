package nextstep.courses.domain.course;

import java.time.LocalDateTime;
import nextstep.courses.domain.base.BaseInfo;
import nextstep.courses.domain.batch.Batch;
import nextstep.courses.domain.batch.Batches;

public class Course {

  private CourseInfo courseInfo;

  private NowBatchNo nowBatchNo;

  private BaseInfo baseInfo;

  public Course() {
  }

  public Course(String title, Long creatorId) {
    this(null, title, 0, creatorId, LocalDateTime.now(), null);
  }

  public Course(Long id, String title, int nowBatchNo, Long creatorId, LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this(new CourseInfo(id, title),
        new NowBatchNo(nowBatchNo),
        new BaseInfo(creatorId, createdAt, updatedAt));
  }

  public Course(CourseInfo courseInfo, NowBatchNo nowBatchNo, BaseInfo baseInfo) {
    this.courseInfo = courseInfo;
    this.nowBatchNo = nowBatchNo;
    this.baseInfo = baseInfo;
  }

  public Batch createdBatch(Long creatorId, Batches batches) {
    nowBatchNo = nowBatchNo.createdBatch();
    Batch batch = new Batch(nowBatchNo.getNowBatchNo(), getId(), creatorId);
    batches.addBatch(batch);
    return batch;
  }

  public Long getId() {
    return courseInfo.getId();
  }

  public String getTitle() {
    return courseInfo.getTitle();
  }

  public int getNowBatchNo() {
    return nowBatchNo.getNowBatchNo();
  }

  public Long getCreatorId() {
    return baseInfo.getCreatorId();
  }
}
