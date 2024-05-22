package nextstep.session.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class PeriodTest {

	private static final LocalDateTime now = LocalDateTime.now();

	@Test
	public void 기간_생성_성공_테스트() {
		Period period = new Period(now,now.plusDays(1));
		assertNotNull(period);
	}
	
	@Test
	public void 기간_생성_실패_테스트() {

		assertThatThrownBy(() -> new Period(now,now.minusDays(1)))
				.isInstanceOf(IllegalArgumentException.class);
	}

}