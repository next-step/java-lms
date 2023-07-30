package nextstep.courses.domain.batch;

import java.time.LocalDateTime;
import nextstep.courses.domain.base.BaseInfo;
import nextstep.courses.domain.curriculum.Curriculum;
import nextstep.courses.domain.curriculum.Curriculums;
import nextstep.courses.domain.session.Session;

public class Batch {

  private BatchInfo batchInfo;

  private Long courseId;

  private BaseInfo baseInfo;

  public Batch() {
  }

  public Batch(int batchNo, Long courseId, Long creatorId) {
    this(null, batchNo, courseId, creatorId, LocalDateTime.now(), null);
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

  public Curriculum addSession(Session session, Curriculums curriculums) {
    Curriculum curriculum = new Curriculum(this, session);
    curriculums.addCurriculum(curriculum);
    return curriculum;
  }

  public boolean hasSession(Session session, Curriculums curriculums) {
    return curriculums.hasCurriculum(new Curriculum(this, session));
  }

  public boolean checkBatchNo(int batchNo) {
    return batchInfo.checkBatchNo(batchNo);
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
