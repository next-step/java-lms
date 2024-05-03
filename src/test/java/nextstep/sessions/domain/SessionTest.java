package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static fixture.sessions.domain.SessionFixture.createSessionWithSessionDate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class SessionTest {
	@DisplayName("강의 종료일이 시작일보다 빠르면 예외가 발생한다.")
	@Test
	void notCourseEndDateEarlierThanStartDate() {
		// given & when & then
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> {
					createSessionWithSessionDate(LocalDate.of(2024, 5, 6), LocalDate.of(2024, 5, 2));
				}).withMessageMatching("강의 종료일보다 강의 시작일이 늦을 수 없습니다.");
	}
}
