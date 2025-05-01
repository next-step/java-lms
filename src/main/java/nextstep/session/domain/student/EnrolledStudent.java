package nextstep.session.domain.student;

public class EnrolledStudent {
    private Long studentId;
    private EnrollmentStatus enrollmentStatus;

    public EnrolledStudent(Long studentId, EnrollmentStatus enrollmentStatus) {
        this.studentId = studentId;
        this.enrollmentStatus = enrollmentStatus;
    }

    public Long getStudentId() {
        return studentId;
    }
    public EnrollmentStatus getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void approve() {
        this.enrollmentStatus = EnrollmentStatus.APPROVED;
    }

    public void reject() {
        this.enrollmentStatus = EnrollmentStatus.REJECTED;
    }
}
