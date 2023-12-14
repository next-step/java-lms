package nextstep.courses.domain.session.student;

import nextstep.courses.domain.session.enums.SelectionStatus;

import java.util.Objects;

import static nextstep.courses.domain.session.enums.SelectionStatus.*;

public class SessionStudent {

    private Long id;
    private Long sessionId;
    private Long nsUserId;
    private SelectionStatus selectionStatus;

    public SessionStudent(Long sessionId, Long nsUserId, SelectionStatus selectionStatus) {
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.selectionStatus = selectionStatus;
    }

    public SessionStudent(Long id, Long sessionId, Long nsUserId, SelectionStatus selectionStatus) {
        this(sessionId, nsUserId, selectionStatus);
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public Long getSessionId() {
        return this.sessionId;
    }

    public Long getNsUserId() {
        return this.nsUserId;
    }

    public SelectionStatus getSelectionStatus() {
        return this.selectionStatus;
    }

    public SessionStudent changeStatus(SelectionStatus selectionStatus) {
        validateSelectionStatus(selectionStatus);

        this.selectionStatus = selectionStatus;
        return this;
    }

    private void validateSelectionStatus(SelectionStatus selectionStatus) {
        if (WAITING.equals(selectionStatus)) {
            throw new IllegalArgumentException("수강생 선별은 승인 또는 거절 중 하나만 선택할 수 있습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionStudent student = (SessionStudent) o;
        return Objects.equals(id, student.id) && Objects.equals(sessionId, student.sessionId) && Objects.equals(nsUserId, student.nsUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionId, nsUserId);
    }
}
