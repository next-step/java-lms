package nextstep.courses.domain;

import nextstep.courses.domain.session.ImageType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageTypeTest {
	@Test
	@DisplayName("of_type 외 String_throw IllegalArgumentException")
	void of_NotAValue() {
		assertThatThrownBy(() -> ImageType.of("txt"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("이미지 타입은 gif, jpg, jpeg, png, svg 만 가능합니다.");
	}

	@Test
	@DisplayName("of_type 존재 String_enum value 반환")
	void of_ExistValue() {
		assertThat(ImageType.of("jpg")).isEqualTo(ImageType.JPG);
	}
}
