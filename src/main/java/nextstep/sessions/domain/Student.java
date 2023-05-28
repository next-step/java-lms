package nextstep.sessions.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Student {

	private final long sessionId;

	private final long nsUserId;

	private LocalDateTime createdAt = LocalDateTime.now();

	private LocalDateTime updatedAt;

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public Student(long sessionId, long nsUserId) {
		this.sessionId = sessionId;
		this.nsUserId = nsUserId;
	}

	public long getSessionId() {
		return sessionId;
	}

	public long getNsUserId() {
		return nsUserId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Student student = (Student)o;
		return sessionId == student.sessionId && nsUserId == student.nsUserId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(sessionId, nsUserId);
	}
}
