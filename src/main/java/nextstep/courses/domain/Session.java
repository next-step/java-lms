package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session extends BaseTimeEntity{
	private Long id;
	private String title;

	public Session(
		Long id, String title,
		LocalDateTime createdAt, LocalDateTime updatedAt
	) {
		super(createdAt, updatedAt);
		this.id = id;
		this.title = title;
	}
}
