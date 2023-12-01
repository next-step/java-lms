package nextstep.courses.domain.enums;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ImageTypeTest {
	@DisplayName("지원하는 이미지 확장자 명이 없을 경우 IllegalArgumentException을 던진다.")
	@Test
	void valid_is_greater_than() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> ImageType.of("mov"));
	}
}
