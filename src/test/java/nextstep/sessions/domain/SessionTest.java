package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class SessionTest {
	@DisplayName("강의 종료일이 시작일보다 빠르면 예외가 발생한다.")
	@Test
	void notCourseEndDateEarlierThanStartDate() {
		// given & when & then
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> {
					new Session(1L, "객체지향 프로그래밍", 1L, LocalDate.of(2024, 5, 2), LocalDate.of(2024, 5, 1), SessionType.PAID, 10000, 100, 10000);
				}).withMessageMatching("강의 종료일보다 강의 시작일이 늦을 수 없습니다.");
	}

	@DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없다")
	@Test
	void paidLectureCannotOverMaximumAttendees() {
		// given & when & then
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> {
					new Session(1L, "객체지향 프로그래밍", 1L, LocalDate.of(2024, 5, 2), LocalDate.of(2024, 5, 6), SessionType.PAID, 100, 101, 10000);
				}).withMessageMatching("유료 강의는 강의 최대 수강 인원을 초과할 수 없다.");

	}

	@DisplayName("무료 강의는 최대 수강 인원 제한이 없다")
	@Test
	void freeLectureCannotOverMaximumAttendees() {
		// given&when
		Session freeSession = new Session(1L, "객체지향 프로그래밍", 1L, LocalDate.of(2024, 5, 2), LocalDate.of(2024, 5, 6), SessionType.FREE, 100, 101, 10000);

		// then
		assertThat(freeSession).isNotNull();
	}

	@DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다")
	@Test
	void paidLectureCanBeAppliedIfPaidAmountEqualsTuitionFee() {
		// given

		// when

		// then
	}
}
