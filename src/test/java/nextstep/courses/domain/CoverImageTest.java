package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoverImageTest {
	@Test
	@DisplayName("생성_이미지 크기 1MB 이하_throw IllegalArgumentException")
	void 이미지크기_1MB_이하() {
		assertThatThrownBy(() -> new CoverImage(0L, 1050L * 1050L, "jpg", new ImageShape()))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("커버 이미지 크기는 1MB 이하여야 합니다.");
	}

	@Test
	@DisplayName("생성_이미지 타입 txt_throw IllegalArgumentException")
	void 이미지타입_확인() {
		assertThatThrownBy(() -> new CoverImage(0L, 1024L * 1024L, "txt", new ImageShape()))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("이미지 타입은 gif, jpg, jpeg, png, svg 만 가능합니다.");
	}
}