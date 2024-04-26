package nextstep.session.domain;

import java.time.LocalDateTime;

public class Period {
	private LocalDateTime startDate;
	private LocalDateTime endDate;

	public Period(LocalDateTime startDate, LocalDateTime endDate) {
		validateDates(startDate, endDate);
		this.startDate = startDate;
		this.endDate = endDate;
	}

	private void validateDates(LocalDateTime startDate, LocalDateTime endDate) {
		if (endDate.isBefore(startDate)) {
			throw new IllegalArgumentException("시작일은 종료일보다 늦을 수 없습니다.");
		}
	}

}
