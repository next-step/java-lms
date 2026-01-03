package nextstep.courses.domain;

import java.util.Objects;

public class Enrollment {
    private final Long id;
    private final Long studentId;
    private final Long sessionId;
    private EnrollmentStatus status;

    public  Enrollment(Long studentId, Long sessionId) {
        this(1L, EnrollmentStatus.APPLIED, studentId, sessionId);
    }

    public Enrollment(Long id, String status, Long studentId, Long sessionId) {
        this(id, EnrollmentStatus.valueOf(status), studentId, sessionId);
    }

    public Enrollment(Long id, EnrollmentStatus status, Long studentId, Long sessionId) {
        this.id = id;
        this.status = status;
        this.studentId = studentId;
        this.sessionId = sessionId;
    }

    public boolean sameSession(Long id) {
        return this.sessionId.equals(id);
    }

    public void validateBelongsTo(Long sessionId) {
        if (!sameSession(sessionId)) {
            throw new IllegalArgumentException("신청하고자 하는 강의가 아닙니다.");
        }
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Enrollment select() {
        if(!isApplied()) {
            throw new IllegalArgumentException("신청 상태에서만 선발할 수 있습니다.");
        }

        this.status = EnrollmentStatus.SELECTED;

        return this;
    }

    public Enrollment approve() {
        if(!isSelected()){
            throw new IllegalArgumentException("선발 상태에서만 승인할 수 있습니다.");
        }
        this.status = EnrollmentStatus.APPROVED;

        return this;
    }

    public Enrollment cancel() {
        this.status = EnrollmentStatus.CANCELED;

        return this;
    }

    public String getEnrollmentStatus() {
        return this.status.toString();
    }

    public boolean isSelected() {
        return status.isSelected();
    }

    public boolean isApplied() {
        return status.isApplied();
    }

    public boolean isApproved() {
        return status.isApproved();
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(id, that.id) && status == that.status && Objects.equals(studentId, that.studentId) && Objects.equals(sessionId, that.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, studentId, sessionId);
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", status=" + status +
                ", studentId=" + studentId +
                ", sessionId=" + sessionId +
                '}';
    }
}
