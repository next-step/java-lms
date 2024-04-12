package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {
	@DisplayName("과정은 기수를 가지고 생성된다.")
	@Test
	void createCourseWithCardinality() {
		// given & when
		Course course = new Course(1L, "tdd 클린코드", 1L);

		// then
		assertThat(course.getId()).isEqualTo(1);
	}

	@DisplayName("과정은 여러 개의 강의를 가지고 생성 된다.")
	@Test
	void CreateCourseWithMultipleSessions() {
		// given & when
		List sessions = List.of(new Session(1L, "객체지향 프로그래밍", 1L, LocalDate.of(2024, 5, 2), LocalDate.of(2024, 5, 30)),
				new Session(2L, "클래스 디자인", 1L, LocalDate.of(2024, 6, 2), LocalDate.of(2024, 2, 20)));

		Course course = new Course(1L, "tdd 클린코드", 1L, sessions);

		// then
		assertThat(course.getSessionsSize()).isEqualTo(2);
	}
}
