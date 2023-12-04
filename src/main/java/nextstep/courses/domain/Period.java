package nextstep.courses.domain;

import java.time.LocalDate;

public class Period {
	private final LocalDate startAt;
	private final LocalDate endAt;

	public Period(LocalDate startAt, LocalDate endAt) {
		this.startAt = startAt;
		this.endAt = endAt;
		validDate();
	}

	public LocalDate getStartAt() {
		return startAt;
	}

	public LocalDate getEndAt() {
		return endAt;
	}

	public void validDate() {
		if (startAt.isAfter(endAt)) {
			throw new IllegalArgumentException("강의 시작일이 강의 종료일 이후일 수 없습니다.");
		}
	}
}
