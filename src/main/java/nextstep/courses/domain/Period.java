package nextstep.courses.domain;

import java.time.LocalDate;

public class Period {
	private final LocalDate startAt;
	private final LocalDate endAt;

	public Period(LocalDate startAt, LocalDate endAt) {
		this.startAt = startAt;
		this.endAt = endAt;
	}

	public LocalDate getStartAt() {
		return startAt;
	}

	public LocalDate getEndAt() {
		return endAt;
	}
}
