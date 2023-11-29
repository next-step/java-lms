package nextstep.courses.domain;

import nextstep.courses.domain.code.Selection;

import java.util.Objects;

public class Student {
    private final long studentId;
    private final long sessionId;
    private Selection selection;

    public Student() {
        this(0L, 0L, Selection.WAITING);
    }

    public Student(long studentId,
                   long sessionId,
                   String selection) {
        this(studentId, sessionId, Selection.valueOf(selection));
    }

    public Student(long studentId,
                   long sessionId,
                   Selection selection) {
        this.studentId = studentId;
        this.sessionId = sessionId;
        this.selection = selection;
    }

    public long getStudentId() {
        return studentId;
    }

    public long getSessionId() {
        return sessionId;
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

    public void approve() {
        this.selection = Selection.APPROVED;
    }

    public void fail() {
        this.selection = Selection.FAILED;
    }

    public Selection getSelection() {
        return this.selection;
    }

}
