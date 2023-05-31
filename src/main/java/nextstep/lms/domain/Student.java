package nextstep.lms.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Student {

    private Long nsUserId;
    private Long sessionId;
    private StudentSelectedType studentSelectedType;
    private StudentApprovedType studentApprovedType;
    private StudentRegisterType studentRegisterType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Student(Long nsUserId, Long sessionId, StudentSelectedType studentSelectedType,
                   StudentApprovedType studentApprovedType, StudentRegisterType studentRegisterType) {
        this(nsUserId, sessionId, studentSelectedType,
                studentApprovedType, studentRegisterType, null, null);
    }

    public Student(Long nsUserId, Long sessionId, StudentSelectedType studentSelectedType,
                   StudentApprovedType studentApprovedType,  StudentRegisterType studentRegisterType,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {

        this.nsUserId = nsUserId;
        this.sessionId = sessionId;
        this.studentSelectedType = studentSelectedType;
        this.studentApprovedType = studentApprovedType;
        this.studentRegisterType = studentRegisterType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Student init(NsUser nsUser, Session session) {
        StudentSelectedType studentSelectedType = StudentSelectedType.NON_SELECTED;
        StudentApprovedType studentApprovedType = StudentApprovedType.NON_APPROVED;
        StudentRegisterType studentRegisterType = StudentRegisterType.NOT_PAID;

        return new Student(nsUser.getId(), session.getId(), studentSelectedType, studentApprovedType, studentRegisterType);
    }

    public void sessionCancel() {
        this.studentRegisterType = StudentRegisterType.CANCELED;
    }

    public StudentSelectedType changeStudentSelect() {
        this.studentSelectedType = StudentSelectedType.SELECTED;
        return studentSelectedType;
    }

    public StudentApprovedType changeApprovedStatus() {
        validateSelectedStudent();
        this.studentApprovedType = StudentApprovedType.APPROVED;
        return studentApprovedType;
    }

    private void validateSelectedStudent() {
        if (!studentSelectedType.equals(StudentSelectedType.SELECTED)) {
            throw new IllegalArgumentException("선발되지 않은 학생은 승인할 수 없습니다.");
        }
    }

    public boolean isNonSelected() {
        return this.studentSelectedType.equals(StudentSelectedType.NON_SELECTED);
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public String getStudentSelectedType() {
        return studentSelectedType.toString();
    }

    public String getStudentApprovedType() {
        return studentApprovedType.toString();
    }

    public String getStudentRegisterType() {
        return studentRegisterType.toString();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
