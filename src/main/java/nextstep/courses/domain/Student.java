package nextstep.courses.domain;

import java.util.Objects;

public class Student {
    private final Long studentId;
    private final Long sessionId;
    private ApprovalState approvalState;

    public Student(Long studentId, Long sessionId) {
        this(studentId, sessionId, ApprovalState.UN_APPROVAL.getCode());
    }
    public Student(Long studentId, Long sessionId, String approvalStateString) {
        this.studentId = studentId;
        this.sessionId = sessionId;
        this.approvalState = ApprovalState.convert(approvalStateString);
    }

    public Student(Long studentId, Long sessionId, ApprovalState approvalState) {
        this.studentId = studentId;
        this.sessionId = sessionId;
        this.approvalState = approvalState;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public boolean isApproved(){
        return approvalState.isApproved();
    }

    public String getApprovalState() {
        return approvalState.name();
    }

    public void approve(){
        this.approvalState = ApprovalState.APPROVAL;
    }

    public void cancel(){
        this.approvalState = ApprovalState.UN_APPROVAL;
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
