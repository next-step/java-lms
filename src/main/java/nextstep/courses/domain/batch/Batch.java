package nextstep.courses.domain.batch;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.courses.domain.base.BaseInfo;
import nextstep.courses.domain.curriculum.Curriculum;
import nextstep.courses.domain.curriculum.Curriculums;
import nextstep.courses.domain.session.Session;

public class Batch {

  private final BatchInfo batchInfo;

  private final Long courseId;

  private final BaseInfo baseInfo;

  public Batch(int batchNo, Long courseId, Long creatorId) {
    this(null, batchNo, courseId, creatorId);
  }

  public Batch(Long id, int batchNo, Long courseId, Long creatorId) {
    this(id, batchNo, courseId
        , creatorId, LocalDateTime.now(), LocalDateTime.now());
  }

  public Batch(Long id, int batchNo, Long courseId, Long creatorId,
      LocalDateTime createdAt, LocalDateTime updatedAt) {
    this(new BatchInfo(id, batchNo), courseId, new BaseInfo(creatorId, createdAt, updatedAt));
  }

  public Batch(BatchInfo batchInfo, Long courseId, BaseInfo baseInfo) {
    this.batchInfo = batchInfo;
    this.courseId = courseId;
    this.baseInfo = baseInfo;
  }

  public Curriculum addSession(Session session, Curriculums curriculums, Long creatorId) {
    Curriculum curriculum = new Curriculum(getId(), session.getId(), creatorId);
    curriculums.addCurriculum(curriculum);
    return curriculum;
  }

  public boolean checkBatchNo(int batchNo) {
    return batchInfo.checkBatchNo(batchNo);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Batch batch = (Batch) o;
    return Objects.equals(batchInfo, batch.batchInfo) && Objects
        .equals(courseId, batch.courseId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(batchInfo, courseId);
  }

  public Long getId() {
    return batchInfo.getId();
  }

  public int getBatchNo() {
    return batchInfo.getBatchNo();
  }

  public Long getCourseId() {
    return courseId;
  }

  public Long getCreatorId() {
    return baseInfo.getCreatorId();
  }
}
