package nextstep.courses.domain;

import nextstep.courses.domain.code.Selection;

import java.util.Objects;

public class Student {
    private final long userId;
    private final long sessionId;
    private Selection selection;

    public Student() {
        this(0L, 0L, Selection.WAITING);
    }

    public Student(long userId,
                   long sessionId,
                   String selection) {
        this(userId, sessionId, Selection.valueOf(selection));
    }

    public Student(long userId,
                   long sessionId,
                   Selection selection) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.selection = selection;
    }

    public long getUserId() {
        return userId;
    }

    public long getSessionId() {
        return sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(userId, student.userId) && Objects.equals(sessionId, student.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, sessionId);
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
