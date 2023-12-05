package nextstep.courses.domain.session.registration;

import java.time.LocalDateTime;

import nextstep.courses.domain.BaseTimeEntity;

public class Registration extends BaseTimeEntity {
	private Long id;
	private final Long userId;
	private final Long sessionId;

	public Registration(
		Long userId, Long sessionId
	) {
		this.userId = userId;
		this.sessionId = sessionId;
	}

	public Registration(
		LocalDateTime createdAt, LocalDateTime updatedAt,
		Long id, Long userId, Long sessionId
	) {
		super(createdAt, updatedAt);
		this.id = id;
		this.userId = userId;
		this.sessionId = sessionId;
	}

	public Long getId() {
		return id;
	}

	public Long getUserId() {
		return userId;
	}

	public Long getSessionId() {
		return sessionId;
	}
}
