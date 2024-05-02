package nextstep.session.domain;

import java.util.Objects;

public class Student {

    private Long id;
    private Long user_id;
    private Long session_id;
    private EnrollmentApprovalStatus approvalStatus;


    public Student(Long user_id, Long session_id) {
        this.user_id = user_id;
        this.session_id = session_id;
        this.approvalStatus = EnrollmentApprovalStatus.HOLD;
    }

    public Student(Long id, Long user_id, Long session_id) {
        this.id = id;
        this.user_id = user_id;
        this.session_id = session_id;
        this.approvalStatus = EnrollmentApprovalStatus.HOLD;

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
        return Objects.equals(id, student.id) && Objects.equals(user_id,
            student.user_id) && Objects.equals(session_id, student.session_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, session_id);
    }

    public Long getUser_id() {
        return user_id;
    }

    public Long getSession_id() {
        return session_id;
    }
}
