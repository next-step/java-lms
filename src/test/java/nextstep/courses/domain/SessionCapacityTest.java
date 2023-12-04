package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionCapacityTest {
	@DisplayName("최대 신청 인원은 0보다 작을 수 없습니다.")
	@Test
	void validate_is_greater_than_zero() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> new SessionCapacity(-30));
	}

	@DisplayName("최대 신청 인원을 초과할 시 IllegalArgumentException을 발생시킵니다.")
	@Test
	void validate_is_greater_than() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> new SessionCapacity(100).isGreaterThan(100));
	}
}
