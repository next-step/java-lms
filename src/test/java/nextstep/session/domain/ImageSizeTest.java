package nextstep.session.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ImageSizeTest {

	@Test
	public void 강의커버이미지_크기_1MB이하_실패_테스트() {
		assertThatThrownBy(() -> new ImageSize(1200))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("1024KB");
	}

	@Test
	public void 강의커버이미지_크기_1MB이하_성공_테스트() {
		ImageSize imageSize = new ImageSize(1024);
		assertNotNull(imageSize); // 생성된 객체가 null이 아닌지 확인
	}

}