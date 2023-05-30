package nextstep.courses.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import nextstep.courses.domain.ApproveStatus;
import nextstep.courses.domain.BaseTimeEntity;


public class SessionEnrollmentEntity extends BaseTimeEntity {

  private Long id;

  private Long sessionId;

  private Long userId;
  private ApproveStatus approveStatus;

  public SessionEnrollmentEntity() {
  }

  public SessionEnrollmentEntity(Long id, Long sessionId, Long userId, ApproveStatus approveStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(createdAt, updatedAt);
    this.id = id;
    this.sessionId = sessionId;
    this.userId = userId;
    this.approveStatus = approveStatus;
  }

  public SessionEnrollmentEntity(Long sessionId, Long userId, ApproveStatus approveStatus) {
    this(null, sessionId, userId, approveStatus, LocalDateTime.now(), LocalDateTime.now());
  }

  public SessionEnrollmentEntity(Long id, Long sessionId, Long userId, String approveStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this(id, sessionId, userId, convertApproveStatus(approveStatus), createdAt, updatedAt);
  }

  /**
   * 아직 데이터 마이그레이션 전, 수강 신청을 먼저 한 사람은 approveStatus가 null이다.
   * 그래서 해당 회원들은 APPROVED로 변경해준다.
   */
  private static ApproveStatus convertApproveStatus(String approveStatus) {
    if (approveStatus == null) {
      return ApproveStatus.APPROVED;
    }
    return ApproveStatus.valueOf(approveStatus);
  }


  public Long getSessionId() {
    return sessionId;
  }

  public Long getUserId() {
    return userId;
  }

  public LocalDateTime getCreatedAt(){
    return createdAt;
  }

  public LocalDateTime getUpdatedAt(){
    return updatedAt;
  }

  public ApproveStatus getApproveStatus() {
    return approveStatus;
  }

  public void approved() {
    this.approveStatus = ApproveStatus.APPROVED;
  }

  public void rejected() {
    this.approveStatus = ApproveStatus.REJECTED;
  }

  public ApproveStatus approveStatus() {
    return approveStatus;
  }

  public Long getId() {
    return id;
  }
}
