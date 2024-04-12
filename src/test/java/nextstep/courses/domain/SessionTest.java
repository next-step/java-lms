package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class SessionTest {
	@DisplayName("강의 종료일이 시작일보다 빠르면 예외가 발생한다.")
	@Test
	void notCourseEndDateEarlierThanStartDate() {
		// given & when & then
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> {
					new Session(1L, "객체지향 프로그래밍", 1L, LocalDate.of(2024, 5, 2), LocalDate.of(2024, 5, 1));
				}).withMessageMatching("강의 종료일보다 강의 시작일이 늦을 수 없습니다.");
	}
}
