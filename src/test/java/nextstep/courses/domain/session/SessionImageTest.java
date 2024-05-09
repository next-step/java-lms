package nextstep.courses.domain.session;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionImageTest {
	@Test
	void 커버이미지_크기가_1MB_이상이면_예외() {
		assertThatThrownBy(() -> new SessionImage(2048, 300, 200, "gif"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("이미지 크기는 1MB 이하여야 합니다.");
	}

	@Test
	void 강의_이미지의_width가_300픽셀_이하면_예외() {
		assertThatThrownBy(() -> new SessionImage(1024, 150, 200, "gif"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("이미지 width는 300픽셀 이상이여야 합니다.");
	}

	@Test
	void 강의_이미지의_height가_200픽셀_이하면_예외() {
		assertThatThrownBy(() -> new SessionImage(1024, 300, 150, "gif"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("이미지 height는 200픽셀 이상이여야 합니다.");
	}

	@Test
	void 허용되지_않는_이미지_타입이면_예외() {
		assertThatThrownBy(() -> new SessionImage(1024, 300, 150, "bmp"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("이미지 height는 200픽셀 이상이여야 합니다.");
	}
}
