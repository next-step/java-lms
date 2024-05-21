package nextstep.session.domain;

import java.util.Objects;

public class Student {

    private Long id;
    private Long userId;
    private Long sessionId;
    private EnrollmentApprovalStatus approvalStatus;

    public Student(Long id, Long userId, Long sessionId,
        EnrollmentApprovalStatus approvalStatus) {
        this.id = id;
        this.userId = userId;
        this.sessionId = sessionId;
        this.approvalStatus = approvalStatus;
    }

    public Student(Long user_id, Long sessionId) {
        this(0L, user_id, sessionId, EnrollmentApprovalStatus.HOLD);
    }

    public Student(Long id, Long userId, Long sessionId, String status) {
        this(id, userId, sessionId, EnrollmentApprovalStatus.convert(status));

    }

    public void approval() {
        this.approvalStatus = EnrollmentApprovalStatus.APPROVED;
    }

    public void cancel() {
        this.approvalStatus = EnrollmentApprovalStatus.CANCELLED;
    }

    public boolean isApproved() {
        return this.approvalStatus == EnrollmentApprovalStatus.APPROVED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(userId,
            student.userId) && Objects.equals(sessionId, student.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, sessionId);
    }

    public Long getUser_id() {
        return userId;
    }

    public Long getSession_id() {
        return sessionId;
    }

    public String getApprovalStatus() {
        return approvalStatus.getDescription();
    }
}
