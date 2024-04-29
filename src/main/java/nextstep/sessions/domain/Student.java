package nextstep.sessions.domain;

import java.util.Objects;

public class Student {
    private final long nsUserId;
    private final long sessionId;
    private boolean isSelected; //선발 상태
    private boolean isApproved; //승인 상태

    public static Student createStudentWithApproved(long nsUserId, long sessionId) {
        return new Student(nsUserId, sessionId, false, true);
    }

    public static Student createStudentWithSelected(long nsUserId, long sessionId) {
        return new Student(nsUserId, sessionId, true, false);
    }

    public Student(long nsUserId, long sessionId) {
        this(nsUserId, sessionId, true, true);
    }

    public Student(long nsUserId, long sessionId, boolean isSelected, boolean isApproved) {
        this.nsUserId = nsUserId;
        this.sessionId = sessionId;
        this.isSelected = isSelected;
        this.isApproved = isApproved;
    }

    public void approve() {
        this.isApproved = true;
    }

    public long getId() {
        return nsUserId;
    }

    public long getNsUserId() {
        return nsUserId;
    }

    public long getSessionId() {
        return sessionId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Student student = (Student) object;
        return nsUserId == student.nsUserId && sessionId == student.sessionId && isSelected == student.isSelected && isApproved == student.isApproved;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nsUserId, sessionId, isSelected, isApproved);
    }
}
