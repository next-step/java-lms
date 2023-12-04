package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ImageSizeTest {

	@DisplayName("이미지의 가로 길이가 최소값 이하일 떄 IllegalArgumentException을 발생시킨다.")
	@Test
	void validate_is_width_over_min() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> new ImageSize(200, 200));
	}

	@DisplayName("이미지의 세로 길이가 최소값 이하일 떄 IllegalArgumentException을 발생시킨다.")
	@Test
	void validate_is_height_over_min() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> new ImageSize(200, 100));
	}

	@DisplayName("이미지의 비율이 규격에 맞지 않을 시 IllegalArgumentException을 발생시킨다.")
	@Test
	void validate_is_ratio_constant() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> new ImageSize(400, 300));
	}
}
