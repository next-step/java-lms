package nextstep.courses.domain.session;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {
	@Test
	void 커버이미지_크기가_1MB_이상이면_예외() {
		assertThatThrownBy(() -> new Session(SessionType.PAID,
				30,
				new SessionImage(2048, 300, 200, "gif"),
				LocalDateTime.of(2024, 4, 20, 12, 12, 12),
				LocalDateTime.of(2024, 5, 20, 12, 12, 12)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("이미지 크기는 1MB 이하여야 합니다.");
	}

	@Test
	void 강의_이미지의_width가_300픽셀_이하면_예외() {
		assertThatThrownBy(() -> new Session(SessionType.PAID,
				30,
				new SessionImage(1024, 150, 200, "gif"),
				LocalDateTime.of(2024, 4, 20, 12, 12, 12),
				LocalDateTime.of(2024, 5, 20, 12, 12, 12)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("이미지 width는 300픽셀 이상이여야 합니다.");
	}

	@Test
	void 강의_이미지의_height가_200픽셀_이하면_예외() {
		assertThatThrownBy(() -> new Session(SessionType.PAID,
				30,
				new SessionImage(1024, 300, 150, "gif"),
				LocalDateTime.of(2024, 4, 20, 12, 12, 12),
				LocalDateTime.of(2024, 5, 20, 12, 12, 12)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("이미지 height는 200픽셀 이상이여야 합니다.");
	}

	@Test
	void 유료_강의의_최대_수강_인원은_0_이하면_예외() {
		assertThatThrownBy(() -> new Session(SessionType.PAID,
				0,
				new SessionImage(1024, 300, 200, "gif"),
				LocalDateTime.of(2024, 4, 20, 12, 12, 12),
				LocalDateTime.of(2024, 5, 20, 12, 12, 12)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("유료 강의의 최대 수강 인원은 1 이상이여야 합니다.");
	}
}
