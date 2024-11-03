package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

import static nextstep.courses.domain.ApprovedStatus.APPROVED;
import static nextstep.courses.domain.ApprovedStatus.DENIED;
import static nextstep.courses.domain.SelectedStatus.REJECTED;
import static nextstep.courses.domain.SelectedStatus.SELECTED;

public class Student {
    private final long id;
    private final long sessionId;
    private final long nsUserId;
    private final SelectedStatus selectedStatus;
    private ApprovedStatus approvedStatus;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Student(long id, long sessionId, long nsUserId, SelectedStatus selectedStatus, ApprovedStatus approvedStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.selectedStatus = selectedStatus;
        this.approvedStatus = approvedStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Student(Session session,
                   NsUser nsUser,
                   SelectedStatus selectedStatus,
                   ApprovedStatus approvedStatus,
                   LocalDateTime createdAt) {
        this(0L, session.getId(), nsUser.getId(), selectedStatus, approvedStatus, createdAt, null);
    }

    public void approved() {
        if (REJECTED.equals(selectedStatus)) {
            return;
        }
        approvedStatus = APPROVED;
    }

    public void denied() {
        if (SELECTED.equals(selectedStatus)) {
            return;
        }
        approvedStatus = DENIED;
    }

    public long getId() {
        return id;
    }

    public long getSessionId() {
        return sessionId;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    public SelectedStatus getSelectedStatus() {
        return selectedStatus;
    }

    public ApprovedStatus getApprovedStatus() {
        return approvedStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student that = (Student) o;
        return id == that.id && sessionId == that.sessionId && nsUserId == that.nsUserId && selectedStatus == that.selectedStatus && approvedStatus == that.approvedStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionId, nsUserId, selectedStatus, approvedStatus);
    }
}
