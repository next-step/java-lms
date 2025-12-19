package nextstep.courses.enrollment.service.dto;

public class EnrollmentSaveRequest {
    private final String paymentId;
    private final Long courseId;
    private final Long cohortId;
    private final Long studentId;
    private final Long sessionId;

    public EnrollmentSaveRequest(String paymentId, Long courseId, Long cohortId, Long studentId,
            Long sessionId) {
        this.paymentId = paymentId;
        this.courseId = courseId;
        this.cohortId = cohortId;
        this.studentId = studentId;
        this.sessionId = sessionId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getCohortId() {
        return cohortId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getSessionId() {
        return sessionId;
    }
}
