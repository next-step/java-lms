package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionPeriod {
	private LocalDateTime startedAt;
	private LocalDateTime finishedAt;

	public SessionPeriod(LocalDateTime startedAt, LocalDateTime finishedAt) {
		validate(startedAt, finishedAt);
		this.startedAt = startedAt;
		this.finishedAt = finishedAt;
	}

	private void validate(LocalDateTime startedAt, LocalDateTime finishedAt) {
		if (startedAt.isEqual(finishedAt)){
			throw new IllegalArgumentException("강의 시작 일시와 종료 일시가 같습니다.");
		}
		if (startedAt.isAfter(finishedAt)){
			throw new IllegalArgumentException("강의 시작 일시가 종료 일시보다 늦습니다.");
		}
	}

	public LocalDateTime startedAt() {
		return startedAt;
	}

	public LocalDateTime finishedAt() {
		return finishedAt;
	}

	@Override
	public String toString() {
		return "SessionPeriod{" +
				"startedAt=" + startedAt +
				", finishedAt=" + finishedAt +
				'}';
	}
}
