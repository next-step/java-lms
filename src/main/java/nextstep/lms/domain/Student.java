package nextstep.lms.domain;

import nextstep.lms.enums.StudentStatusEnum;

import java.util.Objects;

public class Student {
    private final Long userId;
    private final Long sessionId;
    private StudentStatusEnum studentStatusEnum;

    public Student(Long userId, Long sessionId) {
        this(userId, sessionId, StudentStatusEnum.APPLIED);
    }

    public Student(Long userId, Long sessionId, StudentStatusEnum studentStatusEnum) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.studentStatusEnum = studentStatusEnum;
    }

    public boolean isSelected() {
        return studentStatusEnum == StudentStatusEnum.SELECTED;
    }

    public Student selection() {
        this.studentStatusEnum = StudentStatusEnum.SELECTED;
        return this;
    }

    public Student nonSelection() {
        this.studentStatusEnum = StudentStatusEnum.NON_SELECTED;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public String getStudentStatus() {
        return studentStatusEnum.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(userId, student.userId) && Objects.equals(sessionId, student.sessionId) && studentStatusEnum == student.studentStatusEnum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, sessionId, studentStatusEnum);
    }
}
