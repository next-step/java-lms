package nextstep.courses.domain.course;

import java.time.LocalDateTime;
import java.util.Objects;
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
    this(null, title, creatorId);
  }

  public Course(Long id, String title, Long creatorId) {
    this(id, title, 0
        , creatorId, LocalDateTime.now(), LocalDateTime.now());
  }

  public Course(Long id, String title, int nowBatchNo, Long creatorId, LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this(new CourseInfo(id, title)
        , new NowBatchNo(nowBatchNo)
        , new BaseInfo(creatorId, createdAt, updatedAt));
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

  public int getNowBatchNo() {
    return nowBatchNo.getNowBatchNo();
  }

  public Long getCreatorId() {
    return baseInfo.getCreatorId();
  }
}
