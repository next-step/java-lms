package nextstep.students.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Student {

    private Long id;

    private String userId;

    private Long sessionId;

    private StudentApprovalType approvalType;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Student(String userId, Long sessionId) {
        this(null, userId, sessionId, StudentApprovalType.APPLICATION, LocalDateTime.now(), null);
    }

    public Student(Long id, String userId, Long sessionId, StudentApprovalType approvalType, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.sessionId = sessionId;
        this.approvalType = approvalType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void approved() {
        approvalType = StudentApprovalType.APPROVED;
        updatedAt = LocalDateTime.now();
    }

    public void rejected() {
        approvalType = StudentApprovalType.REJECTED;
        updatedAt = LocalDateTime.now();
    }

    public boolean isApproved() {
        return approvalType.isApproved();
    }

    public boolean equalUserId(String userId) {
        return this.userId.equals(userId);
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public StudentApprovalType getApprovalType() {
        return approvalType;
    }

    public String getApprovalTypeName() {
        return approvalType.getName();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student that = (Student) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", sessionId=" + sessionId +
                ", approvalType=" + approvalType +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
