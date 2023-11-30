package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session extends BaseTimeEntity{
	private Long id;
	private String title;
	private Period period;

	public Session(
		Long id, String title, Period period,
		LocalDateTime createdAt, LocalDateTime updatedAt
	) {
		super(createdAt, updatedAt);
		this.id = id;
		this.title = title;
		this.period = period;
	}
}
