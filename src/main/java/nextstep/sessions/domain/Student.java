package nextstep.sessions.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import nextstep.sessions.exception.AlreadyEnrollCancelException;
import nextstep.sessions.exception.EnrollCancelException;
import nextstep.sessions.type.SelectStatusType;

public class Student {

	private final long sessionId;

	private final long nsUserId;

	private boolean deleted;

	private SelectStatusType selectStatusType;

	private LocalDateTime createdAt = LocalDateTime.now();

	private LocalDateTime updatedAt;

	public Student(long sessionId, long nsUserId) {
		this(sessionId, nsUserId, false);
	}

	public Student(long sessionId, long nsUserId, boolean deleted) {
		this(sessionId, nsUserId, deleted, SelectStatusType.UNDER_REVIEW);
	}

	public Student(long sessionId, long nsUserId, boolean deleted, SelectStatusType selectStatusType) {
		this.sessionId = sessionId;
		this.nsUserId = nsUserId;
		this.deleted = deleted;
		this.selectStatusType = selectStatusType;
	}

	public Student enrollCancel() {
		if (this.deleted) {
			throw new AlreadyEnrollCancelException("이미 수강 취소된 수강생 입니다.");
		}
		if (!this.isNotSelected()) {
			throw new EnrollCancelException("선발되지 않은 수강생만 수강 취소할 수 있습니다.");
		}
		this.deleted = true;
		return this;
	}

	public boolean isNotSelected() {
		return this.selectStatusType.isNotSelected();
	}

	public Student changeSelectStatus(SelectStatusType statusType) {
		this.selectStatusType = statusType;
		return this;
	}

	public long getSessionId() {
		return sessionId;
	}

	public long getNsUserId() {
		return nsUserId;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Student student = (Student)o;
		return sessionId == student.sessionId && nsUserId == student.nsUserId && deleted == student.deleted && selectStatusType == student.selectStatusType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(sessionId, nsUserId, deleted, selectStatusType);
	}

	@Override
	public String toString() {
		return "Student[" +
			"sessionId=" + sessionId +
			", nsUserId=" + nsUserId +
			", createdAt=" + createdAt +
			", updatedAt=" + updatedAt +
			']';
	}
}
