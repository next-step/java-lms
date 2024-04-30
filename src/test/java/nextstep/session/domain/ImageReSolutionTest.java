package nextstep.session.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ImageReSolutionTest {

	@Test
	public void 강의커버이미지_해상도_width는300픽셀_height는200픽셀_비율_3대2_성공_테스트() {
		ImageReSolution imageReSolution = new ImageReSolution(300, 200);
		assertNotNull(imageReSolution); // 생성된 객체가 null이 아닌지 확인
	}

	@Test
	public void 강의커버이미지_해상도_비율_3대2_실패_테스트() {
		assertThatThrownBy(() -> new ImageReSolution(305, 200))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("비율");
	}

}