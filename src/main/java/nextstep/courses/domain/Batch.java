package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Batch {

  private Long id;

  private int batchNo;

  private Course course;

  private Sessions sessions = new Sessions();

  private Long creatorId;

  private LocalDateTime createdAt = LocalDateTime.now();

  private LocalDateTime updatedAt;

  public Batch() {
  }

  public Batch(int batchNo, Course course, Long creatorId) {
    this(null, batchNo, course, new Sessions(), creatorId, LocalDateTime.now(), null);
  }

  public Batch(Long id, int batchNo, Course course, Sessions sessions, Long creatorId,
      LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.batchNo = batchNo;
    this.course = course;
    this.sessions = sessions;
    this.creatorId = creatorId;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public void addSession(Session session) {
    sessions.addSession(session);
  }

  public boolean hasSession(Session session) {
    return sessions.hasSession(session);
  }

  public boolean checkBatchNo(int batchNo) {
    return this.batchNo == batchNo;
  }
}
