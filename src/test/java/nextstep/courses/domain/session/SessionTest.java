package nextstep.courses.domain.session;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {
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
