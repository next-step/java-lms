package nextstep.courses.domain.batch;

import java.time.LocalDateTime;
import nextstep.courses.domain.base.BaseInfo;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.curriculum.Curriculum;
import nextstep.courses.domain.curriculum.Curriculums;
import nextstep.courses.domain.session.Session;

public class Batch {

  private BatchInfo batchInfo;

  private Course course;

  private Curriculums curriculums = new Curriculums();

  private BaseInfo baseInfo;

  public Batch() {
  }

  public Batch(int batchNo, Course course, Long creatorId) {
    this(null, batchNo, course, new Curriculums(), creatorId, LocalDateTime.now(), null);
  }

  public Batch(Long id, int batchNo, Course course, Curriculums curriculums, Long creatorId,
      LocalDateTime createdAt, LocalDateTime updatedAt) {
    this(new BatchInfo(id, batchNo), course, curriculums,
        new BaseInfo(creatorId, createdAt, updatedAt));
  }

  public Batch(BatchInfo batchInfo, Course course, Curriculums curriculums,
      BaseInfo baseInfo) {
    this.batchInfo = batchInfo;
    this.course = course;
    this.curriculums = curriculums;
    this.baseInfo = baseInfo;
  }

  public Curriculum addSession(Session session) {
    Curriculum curriculum = new Curriculum(this, session);
    curriculums.addCurriculum(curriculum);
    return curriculum;
  }

  public boolean hasSession(Session session) {
    return curriculums.hasCurriculum(new Curriculum(this, session));
  }

  public boolean checkBatchNo(int batchNo) {
    return batchInfo.checkBatchNo(batchNo);
  }
}
