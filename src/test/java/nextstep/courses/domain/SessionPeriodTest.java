package nextstep.courses.domain;

import nextstep.courses.domain.session.SessionPeriod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionPeriodTest {
	private static final LocalDateTime STARTED_AT = LocalDateTime.of(2023, 11, 1, 0, 0);
	private static final LocalDateTime FINISHED_AT = LocalDateTime.of(2023, 11, 30, 23, 59, 59);
	public static final SessionPeriod NORMAL_SESSION_PERIOD = new SessionPeriod(STARTED_AT, FINISHED_AT);

	@Test
	@DisplayName("생성_시작일시, 종료일시와 같거나 시작일시가 종료일시보다 늦음_throw IllegalArgumentException")
	void 생성() {
		assertThatThrownBy(() -> new SessionPeriod(STARTED_AT, STARTED_AT))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("강의 시작 일시와 종료 일시가 같습니다.");
		assertThatThrownBy(() -> new SessionPeriod(FINISHED_AT, STARTED_AT))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("강의 시작 일시가 종료 일시보다 늦습니다.");
	}
}
