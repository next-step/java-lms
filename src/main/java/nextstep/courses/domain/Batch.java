package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Batch {

  private Long id;

  private int batchNo;

  private Course course;

  private Curriculums curriculums = new Curriculums();

  private Long creatorId;

  private LocalDateTime createdAt = LocalDateTime.now();

  private LocalDateTime updatedAt;

  public Batch() {
  }

  public Batch(int batchNo, Course course, Long creatorId) {
    this(null, batchNo, course, new Curriculums(), creatorId, LocalDateTime.now(), null);
  }

  public Batch(Long id, int batchNo, Course course, Curriculums curriculums, Long creatorId,
      LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.batchNo = batchNo;
    this.course = course;
    this.curriculums = curriculums;
    this.creatorId = creatorId;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
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
    return this.batchNo == batchNo;
  }
}
