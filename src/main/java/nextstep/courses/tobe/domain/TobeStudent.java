package nextstep.courses.tobe.domain;

import nextstep.courses.domain.Session;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class TobeStudent {
    public static final String NOT_ALLOWED_REJECTED_STUDENT_MESSAGE = "탈락된 참가자는 수강신청을 할 수 없습니다.";
    private final long id;
    private final long sessionId;
    private final long nsUserId;
    private final SelectedStatus selectedStatus;
    private final ApprovedStatus approvedStatus;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TobeStudent(long id, long sessionId, long nsUserId, SelectedStatus selectedStatus, ApprovedStatus approvedStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.selectedStatus = selectedStatus;
        this.approvedStatus = approvedStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public TobeStudent(TobeSession session, NsUser nsUser, SelectedStatus selectedStatus, ApprovedStatus approvedStatus, LocalDateTime createdAt) {
        this(0L, session.getId(), nsUser.getId(), selectedStatus, approvedStatus, createdAt, LocalDateTime.now());

    }

    public long getId() {
        return id;
    }

    public long getSessionId() {
        return sessionId;
    }

    public long getNsUserId() {
        return nsUserId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TobeStudent that = (TobeStudent) o;
        return id == that.id && sessionId == that.sessionId && nsUserId == that.nsUserId && selectedStatus == that.selectedStatus && approvedStatus == that.approvedStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionId, nsUserId, selectedStatus, approvedStatus);
    }
}
