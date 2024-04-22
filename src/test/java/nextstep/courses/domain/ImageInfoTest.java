package nextstep.courses.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ImageInfoTest {

	@Test
	public void 강의커버이미지_크기_1MB이하_실패_테스트() {
		assertThatThrownBy(() -> new ImageInfo(1200))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("1024KB");
	}

	@Test
	public void 강의커버이미지_크기_1MB이하_성공_테스트() {
		ImageInfo imageInfo = new ImageInfo(1024);
		assertNotNull(imageInfo); // 생성된 객체가 null이 아닌지 확인
	}

	@ParameterizedTest
	@ValueSource(strings = {"gif", "jpg", "jpeg", "png", "svg"})
	public void 강의커버이미지_타입은_gif_jpg_jpeg_png_svg_성공_테스트(String type) {
		ImageInfo imageInfo = new ImageInfo(type);
		assertNotNull(imageInfo);
	}

	@Test
	public void 강의커버이미지_사이즈_width는300픽셀_height는200픽셀_비율_3대2_성공_테스트() {
		ImageInfo imageInfo = new ImageInfo(300,200);
		assertNotNull(imageInfo); // 생성된 객체가 null이 아닌지 확인
	}

	@Test
	public void 강의커버이미지_사이즈_비율_3대2_실패_테스트() {
		assertThatThrownBy(() -> new ImageInfo(305,200))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("비율");
	}

}