package nextstep.courses.domain;

import nextstep.sessions.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

		List sessionList = List.of(SessionFixture.createSessionWithEnrollment(50, SessionStatus.OPENED),
			SessionFixture.createSessionWithEnrollment(50, SessionStatus.OPENED));
		Sessions sessions = new Sessions(sessionList);
		Course course = new Course(1L, "tdd 클린코드", 1L, sessions);

		// then
		assertThat(course.getSessionsSize()).isEqualTo(2);
	}
}
