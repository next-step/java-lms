package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PeriodTest {
	@DisplayName("강의 시작일이 강의 종료일 이후 일 수 없습니다.")
	@Test
	void valid_is_greater_than_zero() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> new Period(
				LocalDate.of(2023,12,1),
				LocalDate.of(2023,11,30)
				)
			);
	}
}
