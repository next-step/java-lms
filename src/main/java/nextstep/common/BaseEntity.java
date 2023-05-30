package nextstep.common;

import java.time.LocalDateTime;

public class BaseEntity {
	private LocalDateTime createdDate;

	private LocalDateTime updatedDate;

	public BaseEntity() {
		this.createdDate = LocalDateTime.now();
		this.updatedDate = LocalDateTime.now();
	}
}
