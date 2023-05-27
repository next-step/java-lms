package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import nextstep.courses.DuplicatedException;

public class Batch {

  private Long id;

  private int batchNo;

  private Course course;

  private Set<Session> sessions = new HashSet<>();

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

  public void addSession(Session session) {
    validateSession(session);
    sessions.add(session);
  }

  private void validateSession(Session session) {
    if (hasSession(session)) {
      throw new DuplicatedException("해당 기수에 중복되는 강의가 존재합니다.");
    }
  }

  public boolean hasSession(Session session) {
    return sessions.contains(session);
  }

  public boolean checkBatchNo(int batchNo) {
    return this.batchNo == batchNo;
  }


}
