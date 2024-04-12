package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {
	@DisplayName("과정은 기수를 가지고 생성된다.")
	@Test
	void testMethodNameHere() {
		// given & when
		Course course = new Course(1L, "tdd 클린코드", 1L);

		// then
		assertThat(course.getId()).isEqualTo(1);
	}
}
