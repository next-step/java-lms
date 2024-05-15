package nextstep.courses.domain.session;

import nextstep.courses.builder.SessionImageBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionImageTest {
	@Test
	void 커버이미지_크기가_1MB_이상이면_예외() {
		assertThatThrownBy(() -> new SessionImageBuilder().size(2048).build())
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("이미지 크기는 1MB 이하여야 합니다.");
	}

	@Test
	void 강의_이미지의_width가_300픽셀_이하면_예외() {
		assertThatThrownBy(() -> new SessionImageBuilder().width(200).build())
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("이미지 width는 300픽셀 이상이여야 합니다.");
	}

	@Test
	void 강의_이미지의_height가_200픽셀_이하면_예외() {
		assertThatThrownBy(() -> new SessionImageBuilder().height(150).build())
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("이미지 height는 200픽셀 이상이여야 합니다.");
	}

	@Test
	void 허용되지_않는_이미지_타입이면_예외() {
		assertThatThrownBy(() -> new SessionImageBuilder().type("bmp").build())
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("이미지 타입은 gif, jpg(jpeg 포함), png, svg만 허용됩니다.");
	}
}
