package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ImageCapacityTest {
	@DisplayName("이미지 용량 제한을 초과하였습니다.")
	@Test
	void validate_is_over_capacity() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> new ImageCapacity(2.0d));
	}

	@DisplayName("이미지 용량은 0보다 작을 수 없습니다.")
	@Test
	void validate_is_greater_than_zero() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> new ImageCapacity(-3.0d));
	}
}
