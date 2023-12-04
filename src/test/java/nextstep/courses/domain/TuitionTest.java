package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TuitionTest {

	@DisplayName("강의 비용은 0보다 작을 수 없습니다.")
	@Test
	void validate_is_greater_than_zero() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> new Tuition(-3000L));
	}

	@DisplayName("강의 비용과 결제 비용이 일치하지 않습니다.")
	@Test
	void validate_is_equal() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> new Tuition(30000L).isEqual(25000L));
	}
}
