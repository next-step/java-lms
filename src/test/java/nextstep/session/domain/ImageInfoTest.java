package nextstep.session.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ImageInfoTest {

	@ParameterizedTest
	@ValueSource(strings = {"gif", "jpg", "jpeg", "png", "svg"})
	public void 강의커버이미지_타입은_gif_jpg_jpeg_png_svg_성공_테스트(String type) {
		ImageInfo imageInfo = new ImageInfo(type);
		assertNotNull(imageInfo);
	}

}