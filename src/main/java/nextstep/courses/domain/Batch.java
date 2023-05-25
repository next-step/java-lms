package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Batch {

  private Long id;

  private int batchNo;

  private Course course;

  private List<Session> sessions = new ArrayList<>();

  private Long creatorId;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  public Batch() {
  }

  public Batch(int batchNo, Course course, Long creatorId) {
    this(null, batchNo, course, creatorId, LocalDateTime.now(), null);
  }

  public Batch(Long id, int batchNo, Course course, Long creatorId, LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this.id = id;
    this.batchNo = batchNo;
    this.course = course;
    this.creatorId = creatorId;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public void addSession(Session session){
    sessions.add(session);
  }

  public boolean checkBatchNo(int batchNo){
    return this.batchNo == batchNo;
  }
}
