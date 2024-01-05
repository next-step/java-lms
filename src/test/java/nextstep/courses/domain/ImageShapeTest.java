package nextstep.courses.domain;

import nextstep.courses.domain.session.ImageShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageShapeTest {
	public static final ImageShape NORMAL_IMAGE_SHAPE = new ImageShape(300L, 200L);

	@Test
	@DisplayName("생성_이미지 width height 300, 200 미만_throw IllegalArgumentException")
	void 이미지_width_height_값() {
		assertThatThrownBy(() -> new ImageShape(100L, 200L))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("이미지의 width는 300픽셀 이상이어야 합니다.");
		assertThatThrownBy(() -> new ImageShape(300L, 100L))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("이미지의 height는 200픽셀 이상이어야 합니다.");
	}

	@Test
	void 이미지_width_height_비율() {
		assertThatThrownBy(() -> new ImageShape(400L, 200L))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("이미지 width와 height의 비율은 3:2여야 합니다.");
	}
}
