package nextstep.courses.domain.session;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessionPeriod {
	private final LocalDateTime startedAt;
	private final LocalDateTime finishedAt;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SessionPeriod that = (SessionPeriod) o;
		return Objects.equals(startedAt, that.startedAt) && Objects.equals(finishedAt, that.finishedAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(startedAt, finishedAt);
	}
}
