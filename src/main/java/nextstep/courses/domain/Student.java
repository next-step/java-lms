package nextstep.courses.domain;

import java.util.Objects;

public class Student {
    private final Long studentId;
    private final Long sessionId;
    private final ApprovalState approvalState;

    public Student(Long studentId, Long sessionId) {
        this(studentId, sessionId, ApprovalState.UN_APPROVAL.getCode());
    }
    public Student(Long studentId, Long sessionId, String approvalStateString) {
        this.studentId = studentId;
        this.sessionId = sessionId;
        this.approvalState = ApprovalState.convert(approvalStateString);
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public boolean isApproved(){
        return ApprovalState.isApproved(approvalState);
    }

    public String getApprovalState() {
        return approvalState.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId) && Objects.equals(sessionId, student.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, sessionId);
    }
}
