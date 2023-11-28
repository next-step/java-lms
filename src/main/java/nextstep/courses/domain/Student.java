package nextstep.courses.domain;

import nextstep.courses.domain.code.Selection;

import java.util.Objects;

public class Student {
    private final long nsUserId;
    private final long sessionId;
    private Selection selection;

    public Student() {
        this(0L, 0L, Selection.WAITING);
    }

    public Student(long nsUserId,
                   long sessionId,
                   String selection) {
        this(nsUserId, sessionId, Selection.valueOf(selection));
    }

    public Student(long nsUserId,
                   long sessionId,
                   Selection selection) {
        this.nsUserId = nsUserId;
        this.sessionId = sessionId;
        this.selection = selection;
    }

    public long getNsUserId() {
        return nsUserId;
    }

    public long getSessionId() {
        return sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(nsUserId, student.nsUserId) && Objects.equals(sessionId, student.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nsUserId, sessionId);
    }

    public void approve() {
        this.selection = Selection.APPROVED;
    }

    public Selection getSelection() {
        return this.selection;
    }
}
