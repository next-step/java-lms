package nextstep.courses.domain;

import java.time.LocalDateTime;

public class BaseTimeEntity {
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public BaseTimeEntity() {
		createdAt = LocalDateTime.now();
	}

	public BaseTimeEntity(LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}
